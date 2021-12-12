package softuni.adoptdontshop.Web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import softuni.adoptdontshop.Service.BreedService;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Web.exception.ResourceNotFoundException;

import java.security.Principal;

@Controller
public class BreedsController {

    private final BreedService breedService;
    private final DogService dogService;

    public BreedsController(BreedService breedService, DogService dogService) {
        this.breedService = breedService;
        this.dogService = dogService;
    }

    @GetMapping("/breeds/all")
    public String allBreeds(Model model) {
        model.addAttribute("allBreeds",breedService.findAllBreedsWithNamesAndSize());
        return "breeds";
    }

    @GetMapping("/breeds/{id}/details")
    public String breedDetails(@PathVariable Long id, Model model) {
        model.addAttribute("breed", breedService.findBreedById(id));
        model.addAttribute("dogsFromThisBreed",breedService.findAllDogsFromSpecificBreed(id));
        return "breed-details";
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView handleDbExceptions(ResourceNotFoundException e, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("not-found");
        modelAndView.addObject("resourceId", e.getResourceId());
        modelAndView.addObject("user", principal.getName());
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

}
