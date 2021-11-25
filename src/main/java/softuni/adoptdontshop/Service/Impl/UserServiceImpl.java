package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;
import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.UserProfileViewModel;
import softuni.adoptdontshop.Repository.RoleRepository;
import softuni.adoptdontshop.Repository.UserRepository;
import softuni.adoptdontshop.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final SecurityUserServiceImpl securityUserService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, SecurityUserServiceImpl securityUserService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.securityUserService = securityUserService;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    //TODO : to check
    @Override
    public Optional<UserEntity> findUser(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean isUsernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserProfileViewModel findUserByUsername(String userIdentifier) {
        UserEntity userEntity = userRepository
                .findByUsername(userIdentifier)
                .orElseThrow();

        return modelMapper.map(userEntity, UserProfileViewModel.class);
    }

//    @Override
//    public Optional<UserEntity> findUser(Long id) {
//        return this.userRepository
//                .findUserById(id)
//                .map(user -> modelMapper.map(user, UserProfileViewModel.class))
//                .orElse(null);
//    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = roleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = roleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setDescription("I'm Admin Adminov. My job is to maintain this awesome website!")
                    .setEmail("admin@gmail.com")
                    .setAge(31);

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();
            pesho
                    .setUsername("pesho")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setDescription("My name is Petar Petrov. I'm animal lover and want to contribute with anything I can, to support your cause!")
                    .setEmail("pesho@abv.bg")
                    .setAge(15);

            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {

        if (roleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRoleEntity = roleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUserEntity = new UserEntity();

        newUserEntity.
                setUsername(userRegistrationServiceModel.getUsername()).
                setFirstName(userRegistrationServiceModel.getFirstName()).
                setLastName(userRegistrationServiceModel.getLastName()).
                setEmail(userRegistrationServiceModel.getEmail()).
                setAge(userRegistrationServiceModel.getAge()).
                setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword())).
                setRoles(Set.of(userRoleEntity));

        newUserEntity = userRepository.save(newUserEntity);

        //лог-ваме User-ът след регистрация.

        //1.Взимаме UserDetails, които са Spring репрезентация на logged User-ът.
        UserDetails principal = securityUserService.loadUserByUsername(newUserEntity.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUserEntity.getPassword(),
                principal.getAuthorities()
        );

        //Кой е този който текущо се е лог-нал в map-а.
        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }
}
