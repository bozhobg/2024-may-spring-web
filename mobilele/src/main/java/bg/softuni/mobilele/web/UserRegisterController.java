package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.RegisterDTO;
import bg.softuni.mobilele.model.dto.RoleDTO;
import bg.softuni.mobilele.service.RoleService;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.utils.CurrentUser;
import bg.softuni.mobilele.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private final CurrentUser currentUser;

    @Autowired
    public UserRegisterController(
            UserService userService,
            RoleService roleService,
            CurrentUser currentUser) {
        this.userService = userService;
        this.roleService = roleService;
        this.currentUser = currentUser;
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
        if (currentUser.isLoggedIn()) return "redirect:/";

        return "auth-register";
    }

    @PostMapping
    public String postRegister(
            @Valid RegisterDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttr
    ) {
        if (currentUser.isLoggedIn()) return "redirect:/";

        if (bindingResult.hasErrors()) {

            RedirectUtil.setRedirectBindingModelAndResult(
                    rAttr,
                    bindingModel,
                    bindingResult,
                    "registerData"
            );

            return "redirect:/users/register";
        }

        this.userService.register(bindingModel);

        return "redirect:/users/login";
    }
}
