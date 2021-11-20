package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Model.ViewModel.BreedViewModel;
import softuni.adoptdontshop.Repository.BreedRepository;
import softuni.adoptdontshop.Service.BreedService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;

    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
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
}
