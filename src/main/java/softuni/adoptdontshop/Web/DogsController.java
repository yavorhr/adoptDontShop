package softuni.adoptdontshop.Web;

import org.hibernate.annotations.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.adoptdontshop.Model.Model.BindingModel.DogAddBindingModel;
import softuni.adoptdontshop.Model.Model.BindingModel.DogUpdateBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogUpdateServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Model.Model.ViewModel.DogDetailsViewModel;
import softuni.adoptdontshop.Service.BreedService;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.Impl.CurrentUser;
import softuni.adoptdontshop.Web.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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

    // GET

    @GetMapping("/dogs/all")
    public String allDogs(Model model) {

        model.addAttribute("allDogs", dogService.findAllDogs());
        return "all-dogs";
    }

    @GetMapping("/dogs/{id}/details")
    public String dogDetails(@PathVariable Long id, Model model) {
        model.addAttribute("dog", dogService.findDogById(id));
        return "dog-profile";
    }

    // DELETE
    @PreAuthorize("@dogServiceImpl.isAdmin(#principal.name, #id)")
    @DeleteMapping("/dogs/{id}")
    public String deleteOffer(@PathVariable Long id,
                              Principal principal) {
        dogService.deleteOffer(id);
        return "redirect:/dogs/all";
    }

    // EDIT
    @PreAuthorize("@dogServiceImpl.isAdmin(#principal.name, #id)")
    @GetMapping("/dogs/{id}/edit")
    public String editOffer(@PathVariable Long id,
                            Principal principal, Model model) {

        DogDetailsViewModel dogDetailsViewModel = dogService.findById(id, principal.getName());
        DogUpdateBindingModel dogUpdateBindingModel = modelMapper.map(dogDetailsViewModel, DogUpdateBindingModel.class);

        model.addAttribute("dogUpdateBindingModel", dogUpdateBindingModel);
        model.addAttribute(model.addAttribute("allBreedsNames", breedService.findAllBreedsNames()));

        return "update-dog";
    }

    @GetMapping("/dogs/{id}/edit/errors")
    public String editDogProfileErrors(@PathVariable Long id, Model model) {
        model.addAttribute(model.addAttribute("allBreedsNames", breedService.findAllBreedsNames()));
        return "update-dog";
    }

    @PatchMapping("/dogs/{id}/edit")
    public String editDogProfile(
            @PathVariable Long id,
            @Valid DogUpdateBindingModel dogUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("dogUpdateBindingModel", dogUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dogUpdateBindingModel", bindingResult);

            return "redirect:/dogs/" + id + "/edit/errors";
        }

        DogUpdateServiceModel dogUpdateServiceModel = modelMapper.map(dogUpdateBindingModel,
                DogUpdateServiceModel.class);

        dogService.updateDogProfile(dogUpdateServiceModel);

        return "redirect:/dogs/" + id + "/details";
    }

    // ADD
    @GetMapping("/dogs/add")
    public String addDogPage(Model model) {
        if (!model.containsAttribute("dogAlreadyAdded") || !model.containsAttribute("allBreedsNames")) {
            model.addAttribute("allBreedsNames", breedService.findAllBreedsNames());
            model.addAttribute("dogAlreadyAdded", false);
        }
        return "add-dog";
    }

    @PostMapping("/dogs/add")
    public String addDog(@Valid DogAddBindingModel dogAddBindingModel, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) {

        //TODO : final check of the user input BindingModel validations
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("dogAddBindingModel", dogAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.dogAddBindingModel", bindingResult);

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

        DogAddServiceModel dogServiceModel = dogService.addNewDog(dogAddBindingModel, currentUser.getUserIdentifier());
        return "redirect:/profile/" + dogServiceModel.getId() + "/details";
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