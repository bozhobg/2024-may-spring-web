package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    private final RouteService routeService;
    private final PictureService pictureService;
    private final CurrentUser currentUser;

    @Autowired
    public HomeController(
            RouteService routeService,
            PictureService pictureService,
            CurrentUser currentUser
    ) {
        this.routeService = routeService;
        this.pictureService = pictureService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("mostCommented")
    public RouteShortInfoDTO mostCommented() {
        return this.routeService.getMostCommentedRoute();
    }

    @ModelAttribute("imageUrls")
    public List<PictureShortDTO> getUrls() {
        return this.pictureService.getPictureLinks();
    }


    @GetMapping("/")
    public String getIndex() {
        if (currentUser.isLogged()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        return "index";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
