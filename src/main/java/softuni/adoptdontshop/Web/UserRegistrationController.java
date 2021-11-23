package softuni.adoptdontshop.Web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.adoptdontshop.Model.Model.BindingModel.UserRegistrationBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.UserRegistrationServiceModel;
import softuni.adoptdontshop.Service.UserService;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegistrationBindingModel userRegisterBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/users/register")
    public String registerUser(Model model) {
        if (!model.containsAttribute("doesUsernameExist") || !model.containsAttribute("doesEmailAddressExist")) {
            model.addAttribute("doesUsernameExist", false);
            model.addAttribute("doesEmailAddressExist", false);
        }
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(
            @Valid UserRegistrationBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        //TODO : to check why th:if is not showing
        boolean doesUsernameExist = userService.doesUsernameAlreadyExist(userRegisterBindingModel.getUsername());
        boolean doesEmailAddressExist = userService.doesEmailAddressAlreadyExist(userRegisterBindingModel.getEmail());


        if (doesUsernameExist) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
                    .addFlashAttribute("doesUsernameExist", true);
            return "redirect:register";
        }

        if (doesEmailAddressExist) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
                    .addFlashAttribute("doesEmailAddressExist", true);
            return "redirect:register";
        }

        UserRegistrationServiceModel serviceModel =
                modelMapper.map(userRegisterBindingModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}