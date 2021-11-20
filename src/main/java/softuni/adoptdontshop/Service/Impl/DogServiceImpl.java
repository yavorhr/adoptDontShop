package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Repository.BreedRepository;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Service.DogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final ModelMapper modelMapper;
    private final BreedRepository breedRepository;

    public DogServiceImpl(DogRepository dogRepository, ModelMapper modelMapper, BreedRepository breedRepository) {
        this.dogRepository = dogRepository;
        this.modelMapper = modelMapper;
        this.breedRepository = breedRepository;
    }

    @Override
    public List<DogCardView> findDogsHomePage() {
        return dogRepository.findDogsHomePage()
                .stream()
                //TODO : to create one mapping method for dog -> dogViewModel
                .map(
                        dog -> {
                            DogCardView dogCardView = modelMapper.map(dog, DogCardView.class);
                            dogCardView.setBreed(dog.getBreed().getName());
                            dogCardView.setImageUrl(dog.getPictures().get(1).getUrl());
                            return dogCardView;
                        })
                .collect(Collectors.toList());
    }

    @Override
    public List<DogCardView> findAllDogs() {
        return dogRepository
                .findAll()
                .stream()
                .map(dog -> {
                    DogCardView dogCardView = modelMapper.map(dog, DogCardView.class);
                    dogCardView.setBreed(dog.getBreed().getName());
                    dogCardView.setImageUrl(dog.getPictures().get(1).getUrl());
                    return dogCardView;
                })
                .collect(Collectors.toList());
    }

    //TODO : to check if works correct. Think if this is optimal way to check for exisiting dog
    @Override
    public boolean doesDogAlreadyExistInDatabase(String name, String breed, Integer age) {
        boolean findByNameAndAge = dogRepository.findByNameAndAge(name, age).isPresent();
        boolean findByBreed = breedRepository.findByName(name).isPresent();

        return findByNameAndAge && findByBreed;
    }

    @Override
    public void addNewDog(DogAddServiceModel dogServiceModel) {
        Dog dog = modelMapper.map(dogServiceModel, Dog.class);
        dog.setAddedOn(LocalDateTime.now());

        System.out.println(dog);
    }


}
