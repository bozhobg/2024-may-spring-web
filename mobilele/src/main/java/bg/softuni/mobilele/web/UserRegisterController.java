package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.dto.RoleDTO;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.service.RoleService;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("users/register")
public class UserRegisterController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRegisterController(
            UserService userService,
            RoleService roleService
    ) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute(name = "registerData")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute(name = "roles")
    public List<RoleDTO> getRoles() {
        return this.roleService.getRolesAsDTOs();
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

//        TODO: handle errors
        this.userService.registerUser(bindingModel);

        return "redirect:/users/login";
    }
}
