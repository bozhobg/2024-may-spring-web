package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.enums.Level;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class RouteDetailsDTO {
    private Long id;

    private String name;

    private String description;

    private String gpxCoordinates;

    private Level level;

    private String authorFullName;

    private String videoUrl;

    private List<PictureShortDTO> pictures;

    private List<CommentDTO> comments;

    //    TODO: calculate total distance
    //    TODO: comments as collection. Functionality?
    //    TODO: pictures as collection. Resolve storage of new ones - FS, cloud?
    //    Categories not used in this view

    public RouteDetailsDTO() {
        this.pictures = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteDetailsDTO setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RouteDetailsDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public RouteDetailsDTO setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public List<PictureShortDTO> getPictures() {
        return pictures;
    }

    public RouteDetailsDTO setPictures(List<PictureShortDTO> pictures) {
        this.pictures = pictures;
        return this;
    }
}
