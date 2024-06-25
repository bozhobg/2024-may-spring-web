package com.bonappetit.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "addedBy")
    private List<Recipe> addedRecipes;

    @ManyToMany
    private List<Recipe> favouriteRecipes;

    public User() {
        this.addedRecipes = new ArrayList<>();
        this.favouriteRecipes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public User setAddedRecipes(List<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
        return this;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public User setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
        return this;
    }
}
