package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public HomeController(
            UserSession userSession,
            RecipeService recipeService,
            UserService userService
    ) {
        this.userSession = userSession;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex() {
        if (userSession.isLoggedIn()) return "redirect:/home";

        return "index";
    }

    @GetMapping("/home")
    public String getHome(
            Model model
    ) {
        if (!userSession.isLoggedIn()) return "redirect:/users/login";

        Map<CategoryName, List<RecipeInfoDTO>> mapCatRecipes = this.recipeService.getAllRecipesByCategories();

        model.addAttribute("desserts", mapCatRecipes.get(CategoryName.DESSERT));
        model.addAttribute("mainDishes", mapCatRecipes.get(CategoryName.MAIN_DISH));
        model.addAttribute("cocktails", mapCatRecipes.get(CategoryName.COCKTAIL));

        model.addAttribute("favourites", this.userService.getFavourites());

        return "home";
    }
}
