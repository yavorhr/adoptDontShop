package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;

import java.util.Optional;

public interface UserService {
    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    void initializeUsersAndRoles();

    boolean doesUsernameAlreadyExist(String username);

    boolean doesEmailAddressAlreadyExist(String email);

    Optional<UserEntity> findUser(Long id);
}
