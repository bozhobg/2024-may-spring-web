package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    @Autowired
    public HomeController(
            CurrentUser currentUser
    ) {
        this.currentUser = currentUser;
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
