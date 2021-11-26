package softuni.adoptdontshop.Service.Impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;
import softuni.adoptdontshop.Model.Model.BindingModel.DogAddBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogUpdateServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Model.Model.ViewModel.DogDetailsViewModel;
import softuni.adoptdontshop.Repository.*;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.MedicalRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BreedRepository breedRepository;
    private final ShelterRepository shelterRepository;
    private final PictureRepository pictureRepository;
    private final MedicalRecordService medicalRecordService;

    public DogServiceImpl(DogRepository dogRepository, UserRepository userRepository, ModelMapper modelMapper, BreedRepository breedRepository, ShelterRepository shelterRepository, PictureRepository pictureRepository, MedicalRecordService medicalRecordService) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.breedRepository = breedRepository;
        this.shelterRepository = shelterRepository;
        this.pictureRepository = pictureRepository;
        this.medicalRecordService = medicalRecordService;
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
                            dogCardView.setImageUrl(dog.getPictures().get(0).getUrl());
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
                    dogCardView.setImageUrl(dog.getPictures().get(0).getUrl());
                    return dogCardView;
                })
                .collect(Collectors.toList());
    }

    //TODO : to check if works correct. Think if this is optimal way to check for exisiting dog
    @Override
    public boolean doesDogAlreadyExistInDatabase(String name, String breed, Integer age) {
        boolean findByNameAndAge = dogRepository.findByNameAndAge(name, age).isPresent();
        boolean findByBreed = breedRepository.findByName(breed).isPresent();
        return findByNameAndAge && findByBreed;
    }

    @Override
    public DogAddServiceModel addNewDog(DogAddBindingModel dogAddBindingModel, String username) {
        DogAddServiceModel dogAddServiceModel = modelMapper.map(dogAddBindingModel, DogAddServiceModel.class);

        Dog dog = modelMapper.map(dogAddBindingModel, Dog.class);
        dog.setAddedOn(LocalDate.now());
        dog.setBreed(breedRepository.findByName(dogAddServiceModel.getBreed()).orElseThrow());
        dog.setShelter(shelterRepository.findById(1L).orElseThrow());
        dog.setUser(userRepository.findByUsername(username).orElseThrow());
        dog.setMedicalRecord(
                dogAddServiceModel
                        .getMedicalRecord()
                        .stream()
                        .map(medicalRecordService::findMedicalRecord)
                        .collect(Collectors.toList())
        );

        Picture picture = new Picture();
        picture.setUrl(dogAddBindingModel.getImageUrl());
        picture.setTitle(dogAddBindingModel.getName());
        picture.setDog(dog);

        Dog savedDog = dogRepository.save(dog);
        pictureRepository.save(picture);
        return modelMapper.map(savedDog, DogAddServiceModel.class);
    }

    @Transactional
    @Override
    public DogDetailsViewModel findDogById(Long id) {
        return dogRepository
                .findById(id)
                .map(dog -> {
                    DogDetailsViewModel dogDetailsViewModel = new DogDetailsViewModel();
                    modelMapper.map(dog, dogDetailsViewModel);
                    dogDetailsViewModel.setBreed(dog.getBreed().getName());
                    return dogDetailsViewModel;
                })
                .orElse(null);
    }

    @Override
    public void deleteOffer(Long id) {
        dogRepository.deleteById(id);
    }

    @Override
    public boolean isAdmin(String username, Long id) {
        Optional<UserEntity> currentLoggedInUser = userRepository.
                findByUsername(username);
        Optional<Dog> optDog = dogRepository.findById(id);

        if (optDog.isEmpty()) {
            return false;
        }

        return currentLoggedInUser
                .get().
                        getRoles().
                        stream().
                        map(UserRoleEntity::getRole).
                        anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public DogDetailsViewModel findById(Long id, String username) {
        Optional<Dog> optDog = dogRepository.findById(id);
        return modelMapper.map(optDog.get(), DogDetailsViewModel.class);
    }

    @Override
    public void updateDogProfile(DogUpdateServiceModel dogServiceModel) {
        //TODO - exception
        Dog dog = dogRepository
                .findById(dogServiceModel.getId())
                .orElseThrow();
        // .orElseThrow(() -> new ObjectNotFoundException("Dog with id " + dogUpdateServiceModel.getId() + " not found!"));

        dog
                .setName(dogServiceModel.getName())
                .setBreed(breedRepository.findByName(dogServiceModel.getBreed()).orElseThrow())
                .setAge(dogServiceModel.getAge())
                .setWeight(dogServiceModel.getWeight())
                .setCoatLength(dogServiceModel.getCoatLength())
                .setLastModified(LocalDate.now())
                .setDescription(dogServiceModel.getDescription())
                .setMedicalNotes(dogServiceModel.getMedicalNotes())
                .setColour(dogServiceModel.getColour())
                .setGender(dogServiceModel.getGender())
                .setSize(dogServiceModel.getSize())
                .setHouseTrained(dogServiceModel.isHouseTrained())
                .setGetAlongWith(dogServiceModel.getGetAlongWith());

        dog.getMedicalRecord().clear();
        dog.setMedicalRecord(
                dogServiceModel
                        .getMedicalRecord()
                        .stream()
                        .map(medicalRecordService::findMedicalRecord)
                        .collect(Collectors.toList()));

        dogRepository.save(dog);

    }
}



