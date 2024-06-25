package com.paintingscollectors.controller;

import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final PaintingService paintingService;

    @Autowired
    public HomeController(CurrentUser currentUser, PaintingService paintingService) {
        this.currentUser = currentUser;
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String getIndex() {
        if (currentUser.isLogged()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome(
            Model model
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        Long userId = currentUser.getId();

        model.addAttribute("userPaintings", this.paintingService.getUserPaintings(userId));
//        TODO: voted doesn't update home view
        model.addAttribute("favPaintings", this.paintingService.getUserFavs(userId));
        model.addAttribute("otherPaintings", this.paintingService.getOhterPaintings(userId));
        model.addAttribute("topVoted", this.paintingService.getTopVoted());

        return "home";
    }
}
