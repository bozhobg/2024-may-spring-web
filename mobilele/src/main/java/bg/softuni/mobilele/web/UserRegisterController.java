package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.enums.Role;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users/register")
public class UserRegisterController {

    @ModelAttribute(name = "registerData")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute(name = "roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @GetMapping
    public String getRegister() {

        return "auth-register";
    }

    @PostMapping
    public String postRegister(
            @Valid RegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttr
    ) {
        if (bindingResult.hasErrors()) {
            rAttr.addFlashAttribute("registerData", bindingModel);
            rAttr.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData",
                    bindingResult
            );

            return "redirect:/users/register";
        }

//        TODO: register logic

        return "redirect:/users/login";
    }
}
