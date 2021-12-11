package softuni.adoptdontshop.Service;


import softuni.adoptdontshop.Model.Model.BindingModel.DonationBindingModel;
import softuni.adoptdontshop.Model.Model.ViewModel.DonationsViewModel;

import java.util.List;

public interface DonationService {

    void addContribution(DonationBindingModel donationBindingModel);

    List<DonationsViewModel> getAllDonations();
}
