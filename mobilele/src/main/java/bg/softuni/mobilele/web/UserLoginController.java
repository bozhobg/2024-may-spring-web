package bg.softuni.mobilele.web;

import bg.softuni.mobilele.constants.ErrorMessages;
import bg.softuni.mobilele.model.dto.LoginDTO;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.utils.CurrentUser;
import bg.softuni.mobilele.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final CurrentUser currentuser;

    @Autowired
    public UserLoginController(
            UserService userService,
                               CurrentUser currentuser
    ) {
        this.userService = userService;
        this.currentuser = currentuser;
    }


    @ModelAttribute(name = "loginData")
    public LoginDTO loginDTO() {

        return new LoginDTO();
    }

    @GetMapping("/login")
    public String getLogin() {
        if (currentuser.isLoggedIn()) return "redirect:/";

        return "auth-login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid LoginDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (currentuser.isLoggedIn()) return "redirect:/";

        String attrName = "loginData";

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectBindingModelAndResult(
                    rAttrs,
                    bindingModel,
                    bindingResult,
                    attrName
            );

            return "redirect:/users/login";
        }

        boolean success = this.userService.login(bindingModel);

        if (!success) {
            bindingResult.addError(new ObjectError(attrName, ErrorMessages.INVALID_USERNAME_OR_PASSWORD));

            RedirectUtil.setRedirectBindingModelAndResult(
                    rAttrs,
                    bindingModel,
                    bindingResult,
                    attrName
            );

            return "redirect:/users/login";
        }

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String postLogout() {
        if (!currentuser.isLoggedIn()) return "redirect:/users/login";

        this.userService.logout();

        return "redirect:/";
    }
}
