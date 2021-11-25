package softuni.adoptdontshop.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.adoptdontshop.Service.DogService;

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

}
