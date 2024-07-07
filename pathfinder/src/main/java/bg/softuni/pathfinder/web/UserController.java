package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(
            UserService userService,
            CurrentUser currentUser
    ) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/profile")
    public String getProfile(
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        UserProfileDTO profileData = this.userService.getUserProfileData();

        model.addAttribute("profileData", profileData);

        return "profile";
    }

    @PostMapping("/logout")
    public String logout() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        this.userService.logout();

        return "redirect:/";
    }
}
