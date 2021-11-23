package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Model.Model.ViewModel.DogDetailsViewModel;

import java.util.List;

public interface DogService {

    List<DogCardView> findDogsHomePage();

    List<DogCardView> findAllDogs();

    boolean doesDogAlreadyExistInDatabase(String name, String breed, Integer age);

    void addNewDog(DogAddServiceModel dogServiceModel);

    DogDetailsViewModel findDogById(Long id);
}
