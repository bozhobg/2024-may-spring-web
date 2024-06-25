package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.constants.ErrorMessages;
import com.bonappetit.model.dto.RecipeAddDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final UserSession userSession;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(
            UserSession userSession,
            RecipeService recipeService
    ) {
        this.userSession = userSession;
        this.recipeService = recipeService;
    }

    @ModelAttribute("addData")
    public RecipeAddDTO addData() {
        return new RecipeAddDTO();
    }

    @ModelAttribute("errorMessages")
    public ErrorMessages errorMessages() {
        return new ErrorMessages();
    }

    @GetMapping("/add")
    public String getAdd() {
        if (!userSession.isLoggedIn()) return "redirect:/users/login";

        return "recipe-add";
    }

    @PostMapping("/add")
    public String postAdd(
            @Valid RecipeAddDTO bindingModel,
            BindingResult bindingResult,
            RedirectAttributes rAttrs
    ) {
        if (!userSession.isLoggedIn()) return "redirect:/users/login";

        if (bindingResult.hasErrors()) {
            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "addData");

            return "redirect:/recipes/add";
        }

        boolean success = this.recipeService.add(bindingModel);

        if (!success) {
            this.setRedAttrs(rAttrs, bindingResult, bindingModel, "addData");

            return "redirect:/recipes/add";
        }

        return "redirect:/home";
    }

    @PostMapping("/favourites/add/{id}")
    public String addFavourite(@PathVariable("id") Long id) {
        if (!userSession.isLoggedIn()) return "redirect:/users/login";

//        TODO: impl
        this.recipeService.addToFavourites(id);

        return "redirect:/home";
    }

    private <T> void setRedAttrs(
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
