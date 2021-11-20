package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ViewModel.BreedViewModel;

import java.util.List;

public interface BreedService {

    List<BreedViewModel> findAllBreedsNames();
}
