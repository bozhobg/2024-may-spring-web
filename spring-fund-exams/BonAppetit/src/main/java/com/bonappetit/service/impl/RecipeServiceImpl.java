package com.bonappetit.service.impl;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.RecipeAddDTO;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserSession userSession;

    @Autowired
    public RecipeServiceImpl(
            RecipeRepository recipeRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            UserSession userSession
    ) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userSession = userSession;
    }

    @Override
    public boolean add(RecipeAddDTO data) {
        if (!userSession.isLoggedIn()) return false;

        Optional<Category> catByName = this.categoryRepository.findByName(data.getCategoryName());
        Optional<User> userById = this.userRepository.findById(userSession.getId());

        if (userById.isEmpty() || catByName.isEmpty()) return false;

        this.recipeRepository.save(
                new Recipe()
                        .setName(data.getName())
                        .setIngredients(data.getIngredients())
                        .setCategory(catByName.get())
                        .setAddedBy(userById.get())
        );

        return true;
    }

    @Override
    @Transactional
    public Map<CategoryName, List<RecipeInfoDTO>> getAllRecipesByCategories() {
        Map<CategoryName, List<RecipeInfoDTO>> mapCatRecipes = new HashMap<>();

        for (CategoryName name : CategoryName.values()) {

            List<Recipe> allByName = this.recipeRepository.findAllByCategory_Name(name);

            mapCatRecipes.put(name, allByName
                            .stream()
                            .map(r -> new RecipeInfoDTO()
                                    .setId(r.getId())
                                    .setName(r.getName())
                                    .setIngredients(r.getIngredients()))
                            .toList());
        }

        return mapCatRecipes;
    }

    @Override
    public void addToFavourites(Long id) {
        Optional<User> userById = this.userRepository.findById(this.userSession.getId());
        Optional<Recipe> recipeById = this.recipeRepository.findById(id);

        if (userById.isEmpty() || recipeById.isEmpty()) return;

        User user = userById.get();
        user.getFavouriteRecipes().add(recipeById.get());
        this.userRepository.save(user);
    }
}
