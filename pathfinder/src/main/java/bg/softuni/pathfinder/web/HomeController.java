package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        double sofiaTemp = new Random().nextDouble() * 100 - 50;
        model.addAttribute("sofiaTemp", String.format("%.2f", sofiaTemp));

//        TODO: populate landing page data: most commented route, temps (live info), selection of, fix url links

        return "index";
    }
}
