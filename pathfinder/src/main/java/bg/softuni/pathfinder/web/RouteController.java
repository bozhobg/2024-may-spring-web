package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;

    @Autowired
    public RouteController(
            RouteService routeService,
            CurrentUser currentUser
    ) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public String routes(
            Model model
    ) {
//        TODO: unauthenticated access?

        if (!model.containsAttribute("routes")) {
            model.addAttribute("routes", this.routeService.getRoutes());
        }


        return "routes";
    }

    @GetMapping("/add")
    public String getAddRoute() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

//        TODO:

        return "add-route";
    }

    @PostMapping("/add")
    public String postAddRoute() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

//        TODO:

        long routeId = 0L;

        return "redirect:/routes/" + routeId;
    }
}
