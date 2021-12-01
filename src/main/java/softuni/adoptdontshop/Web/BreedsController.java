package softuni.adoptdontshop.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.adoptdontshop.Service.BreedService;

@Controller
public class BreedsController {

    private final BreedService breedService;

    public BreedsController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/breeds/all")
    public String allDogs(Model model) {
        breedService.findAllBreedsWithNamesAndSize();
        return "breeds";
    }
}
