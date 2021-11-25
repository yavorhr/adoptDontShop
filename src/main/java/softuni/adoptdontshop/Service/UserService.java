package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.UserProfileViewModel;

import java.util.Optional;

public interface UserService {
    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    void initializeUsersAndRoles();

    Optional<UserEntity> findUser(Long id);

    boolean isUsernameFree(String userName);

    boolean isEmailFree(String email);

    UserProfileViewModel findUserByUsername(String userIdentifier);
}
