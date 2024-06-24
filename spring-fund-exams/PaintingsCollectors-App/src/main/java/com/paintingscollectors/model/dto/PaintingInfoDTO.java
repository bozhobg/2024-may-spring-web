package com.paintingscollectors.model.dto;

public class PaintingInfoDTO {
    private Long id;
    private String name;
    private String author;
    private String styleName;
    private String ownerUsername;
    private String imageUrl;
    private Integer votes;

    public PaintingInfoDTO() {}

    public Long getId() {
        return id;
    }

    public PaintingInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingInfoDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getStyleName() {
        return styleName;
    }

    public PaintingInfoDTO setStyleName(String styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public PaintingInfoDTO setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PaintingInfoDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getVotes() {
        return votes;
    }

    public PaintingInfoDTO setVotes(Integer votes) {
        this.votes = votes;
        return this;
    }
}
