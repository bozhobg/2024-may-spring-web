package com.likebookapp.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private Mood mood;

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany
    private List<User> userLikes;

    public Post() {
        this.userLikes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public Mood getMood() {
        return mood;
    }

    public Post setMood(Mood mood) {
        this.mood = mood;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Post setUser(User user) {
        this.user = user;
        return this;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }
}
