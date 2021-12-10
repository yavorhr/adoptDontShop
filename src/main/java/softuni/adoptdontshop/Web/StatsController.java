package softuni.adoptdontshop.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.adoptdontshop.Service.DogService;
import softuni.adoptdontshop.Service.StatsService;


@Controller
public class StatsController {

    private final StatsService statsService;
    private final DogService dogService;

    public StatsController(StatsService statsService, DogService dogService) {
        this.statsService = statsService;
        this.dogService = dogService;
    }

    @GetMapping("/admin")
    public ModelAndView statistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
//        modelAndView.addObject("adoptedDogs",)
        modelAndView.setViewName("admin-stats");
        return modelAndView;
    }
}



