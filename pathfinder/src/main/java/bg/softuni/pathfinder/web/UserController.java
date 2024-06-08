package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserController {

    @GetMapping("login")
    public ModelAndView getLogin() {

        return new ModelAndView("login");
    }

    @GetMapping("register")
    public ModelAndView getRegister() {

        return new ModelAndView("register");
    }

    @PostMapping("register")
    public ModelAndView doRegister(
            @Valid UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView mnv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", userRegisterDTO);
            mnv.setViewName("redirect:/register");
        }

        mnv.setViewName("redirect:/users/login");

        return mnv;
    }

}
