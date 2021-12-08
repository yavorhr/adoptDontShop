package softuni.adoptdontshop.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Web.exception.ResourceNotFoundException;

@Controller
public class HomeController {

    private final DogService dogService;

    public HomeController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/")
    public String dogsHomePage(Model model) {
        model.addAttribute("dogsHomePage", dogService.findDogsHomePage());
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact-page";
    }

    @GetMapping("/shelter")
    public String shelterPage() {
        return "shelter";
    }

}
