package com.paintingscollectors.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paintings")
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne(optional = false)
    private Style style;

    @ManyToOne(optional = false)
    private User owner;

    @Column(nullable = false)
    private String imageUrl;

//    TODO: should there be bi-dir relation?
    @ManyToMany
    private Set<User> favedByUsers;

//    TODO: should there be bi-dir relation?
    @ManyToMany
    private Set<User> ratedByUsers;

    @Column(nullable = false)
    private Boolean isFavorite;

    @Column(nullable = false)
    private Boolean hasVotes;

    public Painting() {
        this.favedByUsers = new HashSet<>();
        this.isFavorite = false;
        this.ratedByUsers = new HashSet<>();
        this.hasVotes = false;
    }

    public Long getId() {
        return id;
    }

    public Painting setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Painting setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Painting setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Painting setStyle(Style style) {
        this.style = style;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Painting setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Painting setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<User> getFavedByUsers() {
        return favedByUsers;
    }

    public Painting setFavedByUsers(Set<User> favedByUsers) {
        this.favedByUsers = favedByUsers;
        return this;
    }

    public Set<User> getRatedByUsers() {
        return ratedByUsers;
    }

    public Painting setRatedByUsers(Set<User> ratedByUsers) {
        this.ratedByUsers = ratedByUsers;
        return this;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public Painting setFavorite(Boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public Boolean getHasVotes() {

        return hasVotes;
    }

    public Painting setHasVotes(Boolean hasVotes) {
        this.hasVotes = hasVotes;
        return this;
    }
}
