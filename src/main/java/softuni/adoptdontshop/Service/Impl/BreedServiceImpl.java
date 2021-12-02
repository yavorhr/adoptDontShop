package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.Breed;
import softuni.adoptdontshop.Model.Model.ViewModel.*;
import softuni.adoptdontshop.Repository.BreedRepository;
import softuni.adoptdontshop.Service.BreedService;
import softuni.adoptdontshop.Web.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;
    private final ModelMapper modelMapper;

    public BreedServiceImpl(BreedRepository breedRepository, ModelMapper modelMapper) {
        this.breedRepository = breedRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BreedViewModel> findAllBreedsNames() {

        return breedRepository
                .findAllBreedsSortedByNameAsc()
                .stream()
                .map(breed -> {
                    BreedViewModel breedViewModel = new BreedViewModel();
                    breedViewModel.setId(breed.getId());
                    breedViewModel.setName(breed.getName());
                    return breedViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BreedDetailsViewModel> findAllBreedsWithNamesAndSize() {
        return breedRepository
                .findAll()
                .stream()
                .map(breed -> {
                    BreedDetailsViewModel breedDetailsViewModel = new BreedDetailsViewModel();
                    breedDetailsViewModel.setId(breed.getId());
                    breedDetailsViewModel.setName(breed.getName());
                    breedDetailsViewModel.setImageUrl(breed.getImageUrl());
                    breedDetailsViewModel.setSize(breed.getSize());
                    return breedDetailsViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BreedProfileViewModel findBreedById(Long id) {
        return breedRepository
                .findById(id)
                .map(breed -> modelMapper.map(breed, BreedProfileViewModel.class))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<DogCardView> findAllDogsFromSpecificBreed(Long id) {
        //TODO : throw exception
        Breed breed = breedRepository.findById(id).orElseThrow();
        return breed
                .getDogs()
                .stream()
                .map(dog -> {
                    DogCardView dogCardView = new DogCardView();
                    dogCardView = modelMapper.map(dog, DogCardView.class);
                    dogCardView.setImageUrl(dog.getPictures().get(0).getUrl());
                    return dogCardView;
                }).collect(Collectors.toList());

    }
}

