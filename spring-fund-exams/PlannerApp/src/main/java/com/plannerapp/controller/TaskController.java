package com.plannerapp.controller;

import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.UserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserSession userSession;

    @Autowired
    public TaskController(
            TaskService taskService,
            UserSession userSession
    ) {
        this.taskService = taskService;
        this.userSession = userSession;
    }

    @ModelAttribute("taskData")
    public TaskAddDTO taskData() {
        return new TaskAddDTO();
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!userSession.isLogged()) return "redirect:/users/login";

        return "task-add";
    }

    @PostMapping("/add")
    public String postAdd(
            @Valid TaskAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        String attrName = "taskData";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttributes(rAttrs, bindingResult, bindingModel, attrName);

            return "redirect:/tasks/add";
        }

        boolean success = this.taskService.addTask(bindingModel);

        if (!success) {
            this.setRedirectAttributes(rAttrs, bindingResult, bindingModel, attrName);

            return "redirect:/tasks/add";
        }

        return "redirect:/home";
    }

//        TODO: PostMapping

    @GetMapping("/assign/{id}")
    public String assignTask(
            @PathVariable("id") Long taskId
    ) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.taskService.assignTask(taskId);

        return "redirect:/home";
    }

//    TODO: PostMapping

    @GetMapping("/return/{id}")
    public String returnTask(
            @PathVariable("id") Long taskId
    ) {
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.taskService.returnTask(taskId);

        return "redirect:/home";
    }

//    TODO: DeleteMapping

    @GetMapping("/delete/{id}")
    public String deleteTask(
            @PathVariable("id") Long taskId
    ){
        if (!userSession.isLogged()) return "redirect:/users/login";

        this.taskService.delete(taskId);

        return "redirect:/home";
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
