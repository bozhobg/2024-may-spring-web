package com.paintingscollectors.controller;

import com.paintingscollectors.constants.ErrorMessages;
import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.CurrentUser;
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
    public UserController(UserService userService, CurrentUser currentUser) {
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

        String objName = "loginData";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttrs(rAttrs, bindingModel, bindingResult, objName);

            return "redirect:/users/login";
        }

        boolean validCredentials = this.userService.loginUser(bindingModel);

        if (!validCredentials) {
            bindingResult.addError(new ObjectError(objName, ErrorMessages.USERNAME_PASSWORD_INVALID));
            this.setRedirectAttrs(rAttrs, bindingModel, bindingResult, objName);

            return "redirect:/users/login";
        }

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

        String objName = "registerData";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttrs(rAttrs, bindingModel, bindingResult, objName);

            return "redirect:/users/register";
        }

        boolean success = this.userService.registerUser(bindingModel);

        if (!success) {
            bindingResult.addError(new FieldError(objName, "password", ErrorMessages.PASSWORD_MISMATCH));
            bindingResult.addError(new FieldError(objName, "confirmPassword", ErrorMessages.PASSWORD_MISMATCH));

            this.setRedirectAttrs(rAttrs, bindingModel, bindingResult, objName);

            return "redirect:/users/register";
        }


        return "redirect:/users/login";
    }

//    TODO: @PostMapping with form
    @GetMapping("/logout")
    public String getLogout() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        this.userService.logoutUser();

        return "redirect:/";
    }

    private <T> void setRedirectAttrs(
            RedirectAttributes rAttrs,
            T bindingModel,
            BindingResult bindingResult,
            String attrName
    ) {
        rAttrs.addFlashAttribute(attrName, bindingModel);
        rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult." + attrName,
                bindingResult
        );
    }
}
