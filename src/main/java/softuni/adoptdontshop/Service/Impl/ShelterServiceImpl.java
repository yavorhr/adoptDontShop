package softuni.adoptdontshop.Service.Impl;

import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.Shelter;
import softuni.adoptdontshop.Model.Model.ViewModel.ShelterViewModel;
import softuni.adoptdontshop.Repository.ShelterRepository;
import softuni.adoptdontshop.Service.ShelterService;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public ShelterViewModel getCapacity() {
        ShelterViewModel shelterViewModel = new ShelterViewModel();

        Shelter shelter = shelterRepository.findById(1L).orElseThrow();
        Integer remainingDogs = shelterRepository.findAllAdoptedDogs();

        shelterViewModel.setCapacity(shelter.getCapacity());
        shelterViewModel.setAdopted(remainingDogs);
        shelterViewModel.setFreeSlots(shelter.getCapacity() - remainingDogs);

        return shelterViewModel;
    }
}
