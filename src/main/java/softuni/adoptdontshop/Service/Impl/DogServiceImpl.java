package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Model.ServiceModel.DogAddServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DogCardView;
import softuni.adoptdontshop.Model.Model.ViewModel.DogDetailsViewModel;
import softuni.adoptdontshop.Repository.BreedRepository;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.PictureRepository;
import softuni.adoptdontshop.Repository.ShelterRepository;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.MedicalRecordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final ModelMapper modelMapper;
    private final BreedRepository breedRepository;
    private final ShelterRepository shelterRepository;
    private final PictureRepository pictureRepository;
    private final MedicalRecordService medicalRecordService;

    public DogServiceImpl(DogRepository dogRepository, ModelMapper modelMapper, BreedRepository breedRepository, ShelterRepository shelterRepository, PictureRepository pictureRepository, MedicalRecordService medicalRecordService) {
        this.dogRepository = dogRepository;
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
        boolean findByBreed = breedRepository.findByName(breed).isPresent();
        System.out.println();
        return findByNameAndAge && findByBreed;
    }

    @Override
    public void addNewDog(DogAddServiceModel dogServiceModel) {
        Dog dog = modelMapper.map(dogServiceModel, Dog.class);
        dog.setAddedOn(LocalDateTime.now());
        dog.setBreed(breedRepository.findByName(dogServiceModel.getBreed()).orElseThrow());
        dog.setShelter(shelterRepository.findById(1L).orElseThrow());

        dog.setMedicalRecord(
                dogServiceModel
                        .getMedicalRecord()
                        .stream()
                        .map(medicalRecordService::findMedicalRecord)
                        .collect(Collectors.toSet())
        );

        Picture picture = new Picture();
        picture.setUrl(dogServiceModel.getImageUrl());
        picture.setTitle(dogServiceModel.getName());
        picture.setDog(dog);

        dogRepository.save(dog);
        pictureRepository.save(picture);

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


}
