package softuni.adoptdontshop.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.DonationService;
import softuni.adoptdontshop.Service.ShelterService;
import softuni.adoptdontshop.Service.StatsService;

import java.util.List;


@Controller
public class StatsController {

    private final StatsService statsService;
    private final DogService dogService;
    private final ShelterService shelterService;
    private final DonationService donationService;

    public StatsController(StatsService statsService, DogService dogService, ShelterService shelterService, DonationService donationService) {
        this.statsService = statsService;
        this.dogService = dogService;
        this.shelterService = shelterService;
        this.donationService = donationService;
    }

    @GetMapping("/admin")
    public ModelAndView statistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.addObject("chartData", getChartData());
        modelAndView.addObject("capacity", shelterService.getCapacity());
        modelAndView.addObject("donations", donationService.getAllDonations());
        modelAndView.setViewName("admin-stats");
        return modelAndView;
    }

    private List<List<Object>> getChartData() {
        return List.of(
                List.of("Q1", dogService.findDogsFirstQuarter()),
                List.of("Q2", dogService.findDogsSecondQuarter()),
                List.of("Q3", dogService.findDogsThirdQuarter()),
                List.of("Q4", dogService.findDogsFourthQuarter())
        );
    }
}





