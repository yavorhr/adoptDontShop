package softuni.adoptdontshop.Web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.adoptdontshop.Model.Model.BindingModel.DogAddBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Service.BreedService;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.Impl.CurrentUser;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class DogsController {

    private final DogService dogService;
    private final BreedService breedService;

    public DogsController(DogService dogService, BreedService breedService) {
        this.dogService = dogService;
        this.breedService = breedService;
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

    @DeleteMapping("/dogs/{id}")
    public String deleteOffer(@PathVariable Long id,
                              Principal principal) {

        dogService.deleteOffer(id);

        return "redirect:/dogs/all";
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


    @GetMapping("/dogs/{id}/edit/details")
    public String editOffer(@PathVariable Long id, Model model) {

//        OfferDetailsView offerDetailsView = offerService.findById(id, currentUser.getUserIdentifier());
//        OfferUpdateBindingModel offerModel = modelMapper.map(
//                offerDetailsView,
//                OfferUpdateBindingModel.class
//        );

//        model.addAttribute("offerModel", offerModel);
        return "update-dog";
    }

//    @GetMapping("/offers/{id}/edit/errors")
//    public String editOfferErrors(@PathVariable Long id, Model model) {
//        return "update";
//    }
//
//    @PatchMapping("/offers/{id}/edit")
//    public String editOffer(
//            @PathVariable Long id,
//            @Valid OfferUpdateBindingModel offerModel,
//            BindingResult bindingResult,
//            RedirectAttributes redirectAttributes) {
//
//        if (bindingResult.hasErrors()) {
//
//            redirectAttributes.addFlashAttribute("offerModel", offerModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
//
//            return "redirect:/offers/" + id + "/edit/errors";
//        }
//
//        OfferUpdateServiceModel serviceModel = modelMapper.map(offerModel,
//                OfferUpdateServiceModel.class);
//        serviceModel.setId(id);
//
//        offerService.updateOffer(serviceModel);
//
//        return "redirect:/offers/" + id + "/details";
//    }
}
