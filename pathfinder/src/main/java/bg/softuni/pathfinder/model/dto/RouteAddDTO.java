package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class RouteAddDTO {

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 5, max = 20, message = ErrorMessages.LENGTH_5_20)
    private String name;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 5, message = ErrorMessages.LENGTH_MIN_5)
    private String description;

//    TODO: validate is gpx?
//    TODO: File upload? 2 ways: 1. In dto as MultipartFile field 2. At post as @RequestParam
//    @NotNull
//    private String gpxCoordinates;

    @NotNull(message = ErrorMessages.FIELD_NOT_BLANK)
    private Level level;

//    TODO: custom validation YT API request for id -> 200 OK?
//    11 chars length is not guaranteed in the future by devs
    @Pattern(regexp = "[a-zA-Z0-9_-]{11}", message = ErrorMessages.YOUTUBE_ID)
    private String videoUrl;

    @NotEmpty(message = ErrorMessages.EMPTY_SELECTION)
    private List<CategoryType> categories;

    public RouteAddDTO() {
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public RouteAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

//    public String getGpxCoordinates() {
//        return gpxCoordinates;
//    }
//
//    public RouteAddDTO setGpxCoordinates(String gpxCoordinates) {
//        this.gpxCoordinates = gpxCoordinates;
//        return this;
//    }

    public Level getLevel() {
        return level;
    }

    public RouteAddDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public List<CategoryType> getCategories() {
        return categories;
    }

    public RouteAddDTO setCategories(List<CategoryType> categories) {
        this.categories = categories;
        return this;
    }
}
