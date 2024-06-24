package com.paintingscollectors.model.dto;

import com.paintingscollectors.constants.ErrorMessages;
import com.paintingscollectors.vallidation.StyleValid;
import com.paintingscollectors.model.enums.StyleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PaintingAddDTO {

    @NotBlank(message = ErrorMessages.NAME_BLANK)
    @Size(min = 5, max = 40, message = ErrorMessages.NAME_LENGTH)
    private String name;

    @NotBlank(message = ErrorMessages.AUTHOR_BLANK)
    @Size(min = 5, max = 40, message = ErrorMessages.AUTHOR_LENGTH)
    private String author;

    @NotBlank(message = ErrorMessages.URL_BLANK)
    @Size(max = 150, message = ErrorMessages.URL_LENGTH)
    private String imageUrl;

    @StyleValid(message = ErrorMessages.STYLE_INVALID)
    private StyleName style;

    public PaintingAddDTO() {}

    public String getName() {
        return name;
    }

    public PaintingAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingAddDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PaintingAddDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public PaintingAddDTO setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}
