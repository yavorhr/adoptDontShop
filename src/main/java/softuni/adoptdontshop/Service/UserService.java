package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;

public interface UserService {
    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    void initializeUsersAndRoles();
}
