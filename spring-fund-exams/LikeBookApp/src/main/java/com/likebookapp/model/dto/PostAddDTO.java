package com.likebookapp.model.dto;

import com.likebookapp.constants.ErrorMessages;
import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.validation.annotations.MoodValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostAddDTO {

    @NotBlank(message = ErrorMessages.CONTENT_BLANK)
    @Size(min = 2, max = 150, message = ErrorMessages.CONTENT_LENGTH)
    private String content;

//    @NotNull(message = ErrorMessages.MOOD_EMPTY)
    @MoodValid(message = ErrorMessages.MOOD_INVALID)
    private MoodEnum mood;

    public String getContent() {
        return content;
    }

    public PostAddDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public PostAddDTO setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }
}
