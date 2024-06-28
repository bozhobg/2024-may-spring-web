package bg.softuni.mobilele.web;

import bg.softuni.mobilele.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final CurrentUser currentUser;

    @Autowired
    public OfferController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offer-add";
    }

    @GetMapping("/all")
    public String getAll() {
        if (!this.currentUser.isLoggedIn()) return "redirect:/users/login";

        return "offers";
    }
}
