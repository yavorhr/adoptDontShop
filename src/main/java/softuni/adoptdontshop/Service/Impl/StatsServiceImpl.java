package softuni.adoptdontshop.Service.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Model.ViewModel.StatsView;
import softuni.adoptdontshop.Service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

    private int anonymousRequests;
    private int authRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails){
            authRequests++;
            anonymousRequests++;
        }
    }

    @Override
    public StatsView getStats() {
        return new StatsView(authRequests,anonymousRequests);
    }
}
