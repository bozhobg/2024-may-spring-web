package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class RouteAddDTO {

    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String description;

//    TODO: validate is gpx?
    @NotNull
    private String gpxCoordinates; // TODO: upload file?

    @NotNull
    private Level level;

//    TODO: custom validation, null possible
    private String videoUrl;

    @NotEmpty
    private List<CategoryType> categories;

    public RouteAddDTO() {
        this.categories = new ArrayList<>();
    }

    public @NotBlank @Size(min = 5, max = 20) String getName() {
        return name;
    }

    public RouteAddDTO setName(@NotBlank @Size(min = 5, max = 20) String name) {
        this.name = name;
        return this;
    }

    public @NotBlank @Size(min = 5) String getDescription() {
        return description;
    }

    public RouteAddDTO setDescription(@NotBlank @Size(min = 5) String description) {
        this.description = description;
        return this;
    }

    public @NotNull String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddDTO setGpxCoordinates(@NotNull String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public @NotNull Level getLevel() {
        return level;
    }

    public RouteAddDTO setLevel(@NotNull Level level) {
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

    public @NotEmpty List<CategoryType> getCategories() {
        return categories;
    }

    public RouteAddDTO setCategories(@NotEmpty List<CategoryType> categories) {
        this.categories = categories;
        return this;
    }
}
