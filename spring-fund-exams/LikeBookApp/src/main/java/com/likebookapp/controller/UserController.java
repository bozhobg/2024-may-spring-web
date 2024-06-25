package com.likebookapp.controller;

import com.likebookapp.constants.ErrorMessages;
import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.service.UserService;
import com.likebookapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private final CurrentUser currentUser;

    @Autowired
    public UserController(
            UserService userService,
            CurrentUser currentUser
    ) {
        this.userService = userService;
        this.currentUser = currentUser;
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
        if (currentUser.isLogged()) return "redirect:/home";

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid UserLoginDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (currentUser.isLogged()) return "redirect:/home";

        String attrName = "loginData";

        if (bindingResult.hasErrors()) {
            this.setRedirecgAttrs(rAttrs, bindingModel, bindingResult,attrName);

            return "redirect:/users/login";
        }

        boolean isValid = this.userService.validCredentials(bindingModel);

        if (!isValid) {
            bindingResult.addError(new ObjectError(attrName, ErrorMessages.INVALID_USERNAME_PASSWORD));
            this.setRedirecgAttrs(rAttrs, bindingModel, bindingResult,attrName);

            return "redirect:/users/login";
        }

        this.userService.loginUser(bindingModel.getUsername());

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegister() {
        if (currentUser.isLogged()) return "redirect:/home";

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid UserRegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (currentUser.isLogged()) return "redirect:/home";

        String attrName = "registerData";

        if (bindingResult.hasErrors()) {
            this.setRedirecgAttrs(rAttrs, bindingModel, bindingResult, attrName);

            return "redirect:/users/register";
        }

        boolean success = this.userService.register(bindingModel);

        if (!success) {
            bindingResult.addError(
                    new FieldError(attrName, "password", ErrorMessages.PASSWORD_MISMATCH)
            );
            bindingResult.addError(
                    new FieldError(attrName, "confirmPassword", ErrorMessages.PASSWORD_MISMATCH)
            );
            this.setRedirecgAttrs(rAttrs, bindingModel, bindingResult, attrName);

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();

        return "redirect:/";
    }

    private <T> void setRedirecgAttrs(
            RedirectAttributes rAttrs,
            T bindingModel,
            BindingResult bindingResult,
            String attrName
    ) {

        rAttrs.addFlashAttribute(attrName, bindingModel);
        rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult." + attrName,
                bindingResult);
    }
}
