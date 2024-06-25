package com.likebookapp.controller;

import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PostService postService;
    private final CurrentUser currentUser;

    @Autowired
    public HomeController(
            PostService postService,
            CurrentUser currentUser
    ) {
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String getIndex() {
        if (currentUser.isLogged()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        model.addAttribute("userPosts", this.postService.getUserPosts(currentUser.getId()));
        model.addAttribute("otherPosts", this.postService.getOthersPosts(currentUser.getId()));

        return "home";
    }
}
