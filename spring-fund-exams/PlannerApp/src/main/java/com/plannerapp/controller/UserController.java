package com.plannerapp.controller;

import com.plannerapp.constants.ErrorMessages;
import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.service.UserService;
import com.plannerapp.util.UserSession;
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
    public UserController(UserService userService, UserSession userSession) {
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
        if (userSession.isLogged()) return "redirect:/home";

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid UserLoginDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (userSession.isLogged()) return "redirect:/home";

        String attrName = "loginData";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttributes(rAttrs, bindingResult,bindingModel, attrName);

            return "redirect:/users/login";
        }

        boolean success = this.userService.login(bindingModel);

        if (!success) {
            bindingResult.addError(new ObjectError(attrName, ErrorMessages.INVALID_USERNAME_PASSWORD));
            this.setRedirectAttributes(rAttrs, bindingResult, bindingModel, attrName);

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister() {
        if (userSession.isLogged()) return "redirect:/home";

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid UserRegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (userSession.isLogged()) return "redirect:/home";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttributes(rAttrs, bindingResult, bindingModel, "registerData");

            return "redirect:/users/register";
        }

        boolean success = this.userService.register(bindingModel);

        if (!success) {
            bindingResult.addError(
                    new ObjectError("registerData", ErrorMessages.INVALID_USERNAME_EMAIL_PASSWORD)
            );
            this.setRedirectAttributes(rAttrs, bindingResult, bindingModel, "registerData");

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }

//    TODO: PostMapping

    @GetMapping("logout")
    public String logout() {
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.userService.logout();

        return "redirect:/";
    }

    private <T> void setRedirectAttributes(
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
