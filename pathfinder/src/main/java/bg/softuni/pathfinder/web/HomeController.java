package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.CurrentWeatherDTO;
import bg.softuni.pathfinder.model.dto.PictureShortDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.WeatherService;
import bg.softuni.pathfinder.util.CurrentUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class HomeController {

    private final RouteService routeService;
    private final PictureService pictureService;
    private final WeatherService weatherService;
    private final CurrentUser currentUser;

    @Autowired
    public HomeController(
            RouteService routeService,
            PictureService pictureService,
            WeatherService weatherService,
            CurrentUser currentUser
    ) {
        this.routeService = routeService;
        this.pictureService = pictureService;
        this.weatherService = weatherService;
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

    @ModelAttribute("weatherData")
    public Map<String, CurrentWeatherDTO> getWeatherData() throws JsonProcessingException {
//        disabling realtime Rest consumption to save on api requests count
//        return this.weatherService.getTemps();

        return Map.of(
                "varna", new CurrentWeatherDTO(32.2, "/images/weather-icons/01d.png"),
                "sofia", new CurrentWeatherDTO(26.8, "/images/weather-icons/02n.png")
                );
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
