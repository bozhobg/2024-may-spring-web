package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.PaintingAddDTO;
import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/paintings")
public class PaintingController {

    private final PaintingService paintingService;
    private final CurrentUser currentUser;

    public PaintingController(PaintingService paintingService, CurrentUser currentUser) {
        this.paintingService = paintingService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addData")
    public PaintingAddDTO addData() {
        return new PaintingAddDTO();
    }

    @ModelAttribute("styles")
    public StyleName[] styles() {
        return StyleName.values();
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        return "add-painting";
    }

    @PostMapping("/add")
    public String postAdd(
            @Valid PaintingAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        String objName = "addData";

        if (bindingResult.hasErrors()) {
            this.setRedirectAttrs(rAttrs, bindingModel, bindingResult, objName);

            return "redirect:/paintings/add";
        }

        this.paintingService.addPainting(bindingModel);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removePainting(@PathVariable("id") Long paintingId) {
        if (!currentUser.isLogged()) return "redirect:/users/login";
        if (paintingId == null) return "redirect:/home";

        this.paintingService.removePainting(paintingId);

        return "redirect:/home";
    }

    @GetMapping("/fav/add/{id}")
    public String addToFav(@PathVariable("id") Long paintingId) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        this.paintingService.addToFaves(paintingId);

        return "redirect:/home";
    }

    @GetMapping("/fav/remove/{id}")
    public String removeFromFav(@PathVariable("id") Long paintingId) {
        if (!currentUser.isLogged()) return "redirect:/users/login";

        this.paintingService.removeFromFav(paintingId);

        return "redirect:/home";
    }

    @GetMapping("/vote/add/{id}")
    public String addVote(
            @PathVariable("id") Long paintingId
    ) {
        if (!currentUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.paintingService.votePainting(paintingId);

        return "redirect:/home";
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
