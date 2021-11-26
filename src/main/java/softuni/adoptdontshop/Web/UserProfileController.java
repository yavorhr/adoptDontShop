package softuni.adoptdontshop.Web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.adoptdontshop.Model.Model.ViewModel.UserProfileViewModel;
import softuni.adoptdontshop.Service.Impl.CurrentUser;
import softuni.adoptdontshop.Service.UserService;

@Controller
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/profile")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        System.out.println();
        UserProfileViewModel user = this.userService.findUserByUsername(currentUser.getUserIdentifier());
        model.addAttribute("currentUser", user);
        System.out.println();
        return "user-profile";
    }
}
