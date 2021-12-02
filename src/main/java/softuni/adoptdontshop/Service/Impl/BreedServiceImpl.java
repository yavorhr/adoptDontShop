package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Model.ViewModel.BreedDetailsViewModel;
import softuni.adoptdontshop.Model.Model.ViewModel.BreedViewModel;
import softuni.adoptdontshop.Repository.BreedRepository;
import softuni.adoptdontshop.Service.BreedService;

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
}
