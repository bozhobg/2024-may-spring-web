package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.user.AppUserDetails;
import bg.softuni.pathfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            @AuthenticationPrincipal AppUserDetails userDetails,
            Model model
    ) {
        UserProfileDTO profileData = this.userService.getUserProfileData(userDetails.getId());
        model.addAttribute("profileData", profileData);
        return "profile";
    }

//    TODO: test logout without controller
}
