package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/login")
public class UserLoginController {

//    private final String ATTR_NAME = "loginData";

//    private final UserService userService;

//    @Autowired
//    public UserLoginController(
//            UserService userService
//    ) {
//        this.userService = userService;
//    }

    @GetMapping
    public String get() {
        return "login";
    }

//    @ModelAttribute(ATTR_NAME)
//    public UserLoginDTO userLogin() {
//        return new UserLoginDTO();
//    }
//
//    @PostMapping
//    public String postLogin(
//            @Valid UserLoginDTO bindingModel,
//            BindingResult bindingResult,
//            RedirectAttributes rAttrs
//    ) {
////        TODO: implement login logic and redirect logged-in user to homepage
//        if (bindingResult.hasErrors()) {
//            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_NAME);
//
//            return "redirect:/users/login";
//        }
//
//        boolean success = this.userService.login(bindingModel);
//
//        if (!success) {
//            bindingResult.addError(new FieldError(ATTR_NAME, "password", ErrorMessages.INVALID_LOGIN));
//            RedirectUtil.setRedirectAttrs(rAttrs, bindingModel, bindingResult, ATTR_NAME);
//
//            return "redirect:/users/login";
//        }
//
//        return "redirect:/home";
//    }


}
