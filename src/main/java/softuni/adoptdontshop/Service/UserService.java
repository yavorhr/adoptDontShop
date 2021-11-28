package softuni.adoptdontshop.Service;
import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.UserProfileViewModel;



public interface UserService {

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean isUsernameFree(String userName);

    boolean isEmailFree(String email);

    UserProfileViewModel findUserByUsername(String userIdentifier);
}
