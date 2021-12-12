package softuni.adoptdontshop.Service.Impl;


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
import softuni.adoptdontshop.Web.exception.ResourceNotFoundException;

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
                .filter(dog -> !dog.isAdopted())
                .map(dog -> {
                    DogCardView dogCardView = modelMapper.map(dog, DogCardView.class);
                    dogCardView.setBreed(dog.getBreed().getName());
                    dogCardView.setImageUrl(dog.getPictures().get(0).getUrl());
                    return dogCardView;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean doesDogAlreadyExistInDatabase(String name, String breed, Integer age) {
        boolean findByNameAndAge = dogRepository.findByNameAndAge(name, age).isPresent();
        boolean findByBreed = breedRepository.findByName(breed).isPresent();
        return findByNameAndAge && findByBreed;
    }

    @Override
    public DogAddServiceModel addNewDog(DogAddBindingModel dogAddBindingModel) {
        DogAddServiceModel dogAddServiceModel = modelMapper.map(dogAddBindingModel, DogAddServiceModel.class);

        Dog dog = modelMapper.map(dogAddBindingModel, Dog.class);
        dog.setAddedOn(LocalDate.now());
        dog.setBreed(breedRepository.findByName(dogAddServiceModel.getBreed()).orElseThrow());
        dog.setShelter(shelterRepository.findById(1L).orElseThrow());

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
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteDog(Long id) {
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

        Dog dog = dogRepository
                .findById(dogServiceModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException(dogServiceModel.getId()));

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
                .setGetAlongWith(dogServiceModel.getGetAlongWith())
                .setLastModified(LocalDate.now());

        dog.getMedicalRecord().clear();
        dog.setMedicalRecord(
                dogServiceModel
                        .getMedicalRecord()
                        .stream()
                        .map(medicalRecordService::findMedicalRecord)
                        .collect(Collectors.toList()));

        dogRepository.save(dog);
    }

    @Override
    public DogDetailsViewModel adoptDogById(Long id, String username) {
        return dogRepository
                .findById(id)
                .map(dog -> {
                    dog.setAdopted(true);
                    dog.setAdoptedOn(LocalDate.now());
                    dogRepository.save(dog);

                    DogDetailsViewModel dogDetailsViewModel = new DogDetailsViewModel();
                    modelMapper.map(dog, dogDetailsViewModel);
                    dogDetailsViewModel.setBreed(dog.getBreed().getName());
                    return dogDetailsViewModel;
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Integer findDogsFirstQuarter() {
        Integer adoptedDogsFirstQuarter = dogRepository.findAdoptedDogsFirstQuarter();

        if (adoptedDogsFirstQuarter == null) {
            return 0;
        }
        return dogRepository.findAdoptedDogsFirstQuarter();
    }

    @Override
    public Integer findDogsSecondQuarter() {
        Integer adoptedDogsSecondQuarter = dogRepository.findAdoptedDogsSecondQuarter();

        if (adoptedDogsSecondQuarter == null) {
            return 0;
        }
        return dogRepository.findAdoptedDogsSecondQuarter();
    }

    @Override
    public Integer findDogsThirdQuarter() {
        Integer adoptedDogsThirdQuarter = dogRepository.findAdoptedDogsThirdQuarter();

        if (adoptedDogsThirdQuarter == null) {
            return 0;
        }
        return dogRepository.findAdoptedDogsThirdQuarter();
    }

    @Override
    public Integer findDogsFourthQuarter() {
        Integer adoptedDogsFourthQuarter = dogRepository.findAdoptedDogsFourthQuarter();

        if (adoptedDogsFourthQuarter == null) {
            return 0;
        }
        return dogRepository.findAdoptedDogsFourthQuarter();
    }
}



