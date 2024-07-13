package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.constants.ErrorMessages;
import jakarta.validation.constraints.Size;

public class CommentContentPostDTO {

    @Size(min = 10, message = ErrorMessages.LENGTH_MIN_TEN)
    private String textContent;

    public String getTextContent() {
        return textContent;
    }

    public CommentContentPostDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
