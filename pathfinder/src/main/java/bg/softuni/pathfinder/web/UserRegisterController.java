package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/register")
public class UserRegisterController {

    private final String ATTR_NAME = "registerData";

    private final UserService userService;

    @Autowired
    public UserRegisterController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @ModelAttribute(ATTR_NAME)
    public UserRegisterDTO userRegister() {
        return new UserRegisterDTO();
    }

    @GetMapping
    public String getRegister(Model bindingModel) {

        return "register";
    }

    @PostMapping
    public String postRegister(
            @Valid UserRegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {

        if (bindingResult.hasErrors()) {
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_NAME);

            return "redirect:/users/register";
        }

        boolean success = this.userService.register(bindingModel);

        if (!success) {
//            TODO: might be false for other reasons than non-matching passwords

            bindingResult.addError(new FieldError(ATTR_NAME, "password", ErrorMessages.PASSWORD_MISMATCH));
            bindingResult.addError(new FieldError(ATTR_NAME, "confirmPassword", ErrorMessages.PASSWORD_MISMATCH));
            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_NAME);

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }

}
