package bg.softuni.mobilele.web;

import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final CurrentUser currentUser;

    @Autowired
    public BrandController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/all")
    public String getAll() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "brands";
    }
}
