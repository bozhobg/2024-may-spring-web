package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.constants.ErrorMessages;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final UserSession userSession;

    @Autowired
    public UserController(
            UserService userService,
            UserSession userSession
    ) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @ModelAttribute("loginData")
    public UserLoginDTO loginData() {
        return new UserLoginDTO();
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerData() {
        return new UserRegisterDTO();
    }

    @GetMapping("/login")
    public String getLogin() {
        if (userSession.isLoggedIn()) return "redirect:/home";

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid UserLoginDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (userSession.isLoggedIn()) return "redirect:/home";

        if (bindingResult.hasErrors()) {
            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "loginData");

            return "redirect:/users/login";
        }

        boolean success = this.userService.login(bindingModel);

        if (!success) {
//            TODO: error for invalid username or password
            bindingResult.addError(new ObjectError("loginData", ErrorMessages.USERNAME_OR_PASSWORD_INVALID));

            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "loginData");

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister() {
        if (userSession.isLoggedIn()) return "redirect:/home";

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid UserRegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (userSession.isLoggedIn()) return "redirect:/home";

        if (bindingResult.hasErrors()) {
            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "registerData");

            return "redirect:/users/register";
        }

        boolean success = this.userService.register(bindingModel);

        if (!success) {
            bindingResult.addError(new ObjectError("registerData", ErrorMessages.USERNAME_EMAIL_OR_PASSWORD_INVALID));
            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "registerData");
//            TODO: add uniqueness errors, add password mismatch errors, corresponding rendering (group fields)

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }

    @PostMapping("/logout")
    public String postLogout() {
//        TODO: navbar logout form
        if (!userSession.isLoggedIn()) return "redirect:/login";

        this.userSession.logout();

        return "redirect:/";
    }

    private <T> void setRedAttrs(
            RedirectAttributes rAttrs,
            BindingResult bindingResult,
            T bindingModel,
            String attrName
    ) {
        rAttrs.addFlashAttribute(attrName, bindingModel);
        rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult." + attrName,
                bindingResult
        );

    }
}
