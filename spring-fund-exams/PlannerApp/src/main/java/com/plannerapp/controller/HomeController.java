package com.plannerapp.controller;

import com.plannerapp.service.TaskService;
import com.plannerapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final TaskService taskService;

    public HomeController(
            UserSession userSession,
            TaskService taskService
    ) {
        this.userSession = userSession;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getIndex() {
        if (userSession.isLogged()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        model.addAttribute("userTasks", this.taskService.getUserTasks(userSession.getId()));
        model.addAttribute("unassignedTasks", this.taskService.getUnassignedTasks());

        return "home";
    }
}
