package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ViewModel.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
