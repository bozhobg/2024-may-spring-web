package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.dto.UserLoginDTO;
import bg.softuni.pathfinder.service.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView getLogin(Model bindingModel) {
        String dataKey = "loginData";
        Object dataValue = bindingModel.getAttribute("loginData");

        ModelAndView mnv = new ModelAndView("login");
        mnv.addObject(dataKey, dataValue == null ? new UserLoginDTO() : dataValue);

        return mnv;
    }

    @PostMapping("login")
    public ModelAndView doLogin(
            @Valid UserLoginDTO loginData,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
//        TODO: implement login logic and redirect logged-in user to homepage
        ModelAndView mnv = new ModelAndView();

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("loginData", loginData);
            mnv.setViewName("redirect:/users/login");

            return mnv;
        }

        mnv.setViewName("redirect:/");

        return mnv;
    }


    @GetMapping("register")
    public ModelAndView getRegister(Model bindingModel) {
        String dataKey = "registerData";
        Object dataValue = bindingModel.getAttribute(dataKey);

        ModelAndView mnv = new ModelAndView("register");
        mnv.addObject(dataKey, dataValue == null ? new UserRegisterDTO() : dataValue);

        return mnv;
    }

    @PostMapping("register")
    public ModelAndView doRegister(
            @Valid UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView mnv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterDTO);
//            TODO: Error handling
            mnv.setViewName("redirect:/users/register");
            return mnv;
        }

        mnv.setViewName("redirect:/users/login");

        return mnv;
    }

}
