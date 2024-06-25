package com.likebookapp.model.dto;

public class PostInfoDTO {
    private Long id;
    private String content;
    private String mood;
    private int likes;
    private String author;
    private boolean likedByUser;

    public PostInfoDTO() {}

    public Long getId() {
        return id;
    }

    public PostInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostInfoDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public PostInfoDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public PostInfoDTO setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PostInfoDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public PostInfoDTO setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
        return this;
    }
}
