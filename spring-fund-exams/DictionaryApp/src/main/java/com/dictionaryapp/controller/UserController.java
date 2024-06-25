package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.constants.ErrorMessages;
import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.service.UserService;
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
    private final UserSession userSession;

    @Autowired
    public UserController(
            UserService userService,
            UserSession userSession
    ) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @ModelAttribute(name = "registerData")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute(name = "loginData")
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }

    @ModelAttribute(name = "errorMessages")
    public ErrorMessages errorMessages() {
        return new ErrorMessages();
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid UserLoginDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("loginData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginData",
                    bindingResult
            );

            return "redirect:/users/login";
        }

        boolean success = this.userService.login(bindingModel);

        if (!success) {
//            TODO: login error rendering logic, custom validation, global errors
            bindingResult.addError(new FieldError("loginData", "username", ErrorMessages.INVALID_LOGIN));
            bindingResult.addError(new FieldError("loginData", "password", ErrorMessages.INVALID_LOGIN));

            rAttrs.addFlashAttribute("loginData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginData",
                    bindingResult
            );

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout() {
        this.userService.logout();

        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegister() {

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid UserRegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {

        if (bindingResult.hasErrors()) {
            rAttrs.addFlashAttribute("registerData", bindingModel);
            rAttrs.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData",
                    bindingResult
            );

//            TODO: sort out error messages, custom validator and anno
//            TODO: show single error message
//            TODO: tie together password and confirmPassword fields to both show errors on validation or mismatch

            return "redirect:/users/register";
        }

//        TODO: validation on existing user or email with appropriate messages
//        TODO: registration logic


        boolean success = this.userService.register(bindingModel);

        if (!success) {
            rAttrs.addFlashAttribute("registerData", bindingModel);

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }
}
