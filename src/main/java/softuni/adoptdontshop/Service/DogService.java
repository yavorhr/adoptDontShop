package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.BindingModel.DogAddBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogUpdateServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Model.Model.ViewModel.DogDetailsViewModel;

import java.util.List;

public interface DogService {

    List<DogCardView> findDogsHomePage();

    List<DogCardView> findAllDogs();

    boolean doesDogAlreadyExistInDatabase(String name, String breed, Integer age);

    DogAddServiceModel addNewDog(DogAddBindingModel dogAddBindingModel);

    DogDetailsViewModel findDogById(Long id);

    void deleteOffer(Long id);

    boolean isAdmin(String username, Long id);

    DogDetailsViewModel findById(Long id,String username);

    void updateDogProfile(DogUpdateServiceModel dogUpdateServiceModel);

    DogDetailsViewModel adoptDogById(Long id, String username);
}
