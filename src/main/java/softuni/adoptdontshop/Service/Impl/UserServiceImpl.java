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
