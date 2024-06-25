package com.bonappetit.model.dto;

import com.bonappetit.model.entity.CategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecipeAddDTO {
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @Size(min = 2, max = 150)
    private String ingredients;

//    NotEmpty can't validate the enum
//    TODO: What if different value from enum.values() is passed
    @NotNull
    private CategoryName categoryName;

    public RecipeAddDTO() {}

    public String getName() {
        return name;
    }

    public RecipeAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeAddDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public RecipeAddDTO setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
