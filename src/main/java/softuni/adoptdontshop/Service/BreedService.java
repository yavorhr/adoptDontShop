package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ViewModel.BreedDetailsViewModel;
import softuni.adoptdontshop.Model.Model.ViewModel.BreedProfileViewModel;
import softuni.adoptdontshop.Model.Model.ViewModel.BreedViewModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;

import java.util.List;

public interface BreedService {

    List<BreedViewModel> findAllBreedsNames();

    List<BreedDetailsViewModel> findAllBreedsWithNamesAndSize();

    BreedProfileViewModel findBreedById(Long id);

    List<DogCardView> findAllDogsFromSpecificBreed(Long id);

}
