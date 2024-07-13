package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.service.UserService;
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

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(
            Model model
    ) {
        UserProfileDTO profileData = this.userService.getUserProfileData();
        model.addAttribute("profileData", profileData);
        return "profile";
    }

    @PostMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
