package com.bonappetit.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryName name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {
        this.recipes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryName getName() {
        return name;
    }

    public Category setName(CategoryName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Category setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}
