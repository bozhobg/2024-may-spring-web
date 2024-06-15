package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/login")
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute(name = "loginData")
    public LoginDTO loginDTO() {

        return new LoginDTO();
    }

    @GetMapping
    public String getLogin() {

        return "auth-login";
    }

    @PostMapping
    public String postLogin(
            @Valid LoginDTO bindingModel,
            RedirectAttributes rAttrs
    ) {
//        TODO: create current/logged user (log user in)
//        TODO: throw and error handling for invalid credentials
//        TODO: where do we through and handle errors - controllers / services
        boolean success = this.userService.loginUser(bindingModel);
        if (!success) {
            rAttrs.addFlashAttribute("loginData", bindingModel);

            return "redirect:/users/login";
        }

        return "redirect:/";
    }
}
