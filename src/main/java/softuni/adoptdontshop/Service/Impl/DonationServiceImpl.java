package softuni.adoptdontshop.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.Donation;
import softuni.adoptdontshop.Model.Model.BindingModel.DonationBindingModel;
import softuni.adoptdontshop.Model.Model.ServiceModel.DonationServiceModel;
import softuni.adoptdontshop.Repository.DonationRepository;
import softuni.adoptdontshop.Service.DonationService;

import java.time.LocalDate;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final ModelMapper modelMapper;

    public DonationServiceImpl(DonationRepository donationRepository, ModelMapper modelMapper) {
        this.donationRepository = donationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addContribution(DonationBindingModel donationBindingModel) {
        DonationServiceModel donationServiceModel =
                modelMapper.map(donationBindingModel,DonationServiceModel.class);

        donationServiceModel.setContributedOn(LocalDate.now());
        donationRepository.save(modelMapper.map(donationServiceModel, Donation.class));
    }
}
