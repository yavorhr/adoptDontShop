package softuni.adoptdontshop.Web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.adoptdontshop.Model.Model.BindingModel.DogAddBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Service.BreedService;
import softuni.adoptdontshop.Service.DogService;

import javax.validation.Valid;

@Controller
public class DogsController {

    private final DogService dogService;
    private final BreedService breedService;
    private final ModelMapper modelMapper;

    public DogsController(DogService dogService, BreedService breedService, ModelMapper modelMapper) {
        this.dogService = dogService;
        this.breedService = breedService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public DogAddBindingModel dogAddBindingModel() {
        return new DogAddBindingModel();
    }

    @GetMapping("/dogs/all")
    public String allDogs(Model model) {
        model.addAttribute("allDogs", dogService.findAllDogs());
        return "all-dogs";
    }

    @GetMapping("/admin/add")
    public String addDogPage(Model model) {
        if (!model.containsAttribute("dogAlreadyAdded") || !model.containsAttribute("allBreedsNames")) {
            model.addAttribute("allBreedsNames", breedService.findAllBreedsNames());
            model.addAttribute("dogAlreadyAdded", false);
        }
        return "add-dog";
    }

    @PostMapping("/admin/add")
    public String addDog(@Valid DogAddBindingModel dogAddBindingModel, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        //TODO : final check of the user input BindingModel validations
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("dogAddBindingModel", dogAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.dogAddBindingModel", bindingResult);
            System.out.println();
            return "redirect:add";
        }

        boolean dogAlreadyAdded = dogService.doesDogAlreadyExistInDatabase(dogAddBindingModel.getName(), dogAddBindingModel.getBreed(), dogAddBindingModel.getAge());

        if (dogAlreadyAdded) {
            redirectAttributes
                    .addFlashAttribute("dogAddBindingModel", dogAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.dogAddBindingModel", bindingResult)
                    .addFlashAttribute("allBreedsNames", breedService.findAllBreedsNames())
                    .addFlashAttribute("dogAlreadyAdded", "true");
            return "redirect:add";
        }

        System.out.println();
        DogAddServiceModel dogServiceModel = modelMapper.map (dogAddBindingModel,DogAddServiceModel.class);
        dogService.addNewDog(dogServiceModel);

        return "redirect:dogs/all";
    }

    @GetMapping("/profile/{id}")
    public String dogDetails(@PathVariable Long id, Model model) {

        model.addAttribute("dog", dogService.findDogById(id));
        System.out.println();
        return "dog-profile";
    }
}
