package softuni.adoptdontshop.Web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Model.BindingModel.PictureBindingModel;
import softuni.adoptdontshop.Model.Model.ViewModel.UserProfileViewModel;
import softuni.adoptdontshop.Service.CloudinaryService;
import softuni.adoptdontshop.Service.Impl.CurrentUser;
import softuni.adoptdontshop.Service.UserService;

import java.io.IOException;

@Controller
public class UserProfileController {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public UserProfileController(UserService userService, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/users/profile")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        System.out.println();
        UserProfileViewModel user = this.userService.findUserByUsername(currentUser.getUserIdentifier());
        model.addAttribute("currentUser", user);
        System.out.println();
        return "user-profile";
    }

    @Transactional
    @PostMapping("/users/profile")
    public String addPicture(PictureBindingModel pictureBindingModel) throws IOException {
        cloudinaryService.savePicture(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());

        return "redirect:users/profile";
    }




}
