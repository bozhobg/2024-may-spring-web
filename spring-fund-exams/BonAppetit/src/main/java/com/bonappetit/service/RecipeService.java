package com.bonappetit.service;

import com.bonappetit.model.dto.RecipeAddDTO;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.CategoryName;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RecipeService {
    boolean add(RecipeAddDTO data);

    Map<CategoryName, List<RecipeInfoDTO>> getAllRecipesByCategories();

    @Transactional
    void addToFavourites(Long id);
}
