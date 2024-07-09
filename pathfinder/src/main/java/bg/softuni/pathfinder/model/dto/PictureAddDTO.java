package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.validation.FileNotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

public class PictureAddDTO {

    @NotNull
    @Positive
    private Long routeId;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    private String title;

    @NotNull(message = ErrorMessages.FILE_EMPTY)
    @FileNotEmpty
    private MultipartFile picture;

    public PictureAddDTO() {}

    public Long getRouteId() {
        return routeId;
    }

    public PictureAddDTO setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureAddDTO setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
