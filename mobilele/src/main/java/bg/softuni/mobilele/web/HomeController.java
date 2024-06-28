package bg.softuni.mobilele.web;

import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    @Autowired
    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping
    public String getIndex() {

        return "index";
    }

}
