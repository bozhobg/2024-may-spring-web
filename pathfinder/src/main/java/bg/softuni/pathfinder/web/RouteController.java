package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("routes")
    public ModelAndView routes() {
        ModelAndView mnv = new ModelAndView();

        mnv.setViewName("routes");
        mnv.addObject("routes", this.routeService.getRoutes());

        return mnv;
    }
}
