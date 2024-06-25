package com.bonappetit.model.dto;

public class RecipeInfoDTO {

    private Long id;
    private String name;
    private String ingredients;

    public RecipeInfoDTO() {}

    public Long getId() {
        return id;
    }

    public RecipeInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RecipeInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeInfoDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
