package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.RouteAddDTO;
import bg.softuni.pathfinder.model.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getAddRoute(
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

//        TODO:
        model.addAttribute("levels", Level.values());
        model.addAttribute("categories", CategoryType.values());

        if (!model.containsAttribute("routeData")) {
            model.addAttribute("routeData", new RouteAddDTO());
        }

        return "add-route";
    }

    @PostMapping("/add")
    public String postAddRoute() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

//        TODO:

        long routeId = 0L;

        return "redirect:/routes/" + routeId;
    }


    @GetMapping("/{id}")
    public String getDetails(
            @PathVariable Long id,
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        RouteDetailsDTO routeData = this.routeService.getRouteDetails(id);

        if (routeData == null) return "redirect:/routes";

        model.addAttribute("routeDetails", routeData);

//        TODO: provide/post/approve comments through rest, render using js, gpx map, video iframe, upload pic, download gpx

        return "route-details";
    }


}
