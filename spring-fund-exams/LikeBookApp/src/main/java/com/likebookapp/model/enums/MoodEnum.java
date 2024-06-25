package com.likebookapp.model.enums;

public enum MoodEnum {
    HAPPY("This post makes me smile!"),
    SAD("Dejected and discouraged!"),
    INSPIRED("Ready to conquer!");

    private final String description;

    MoodEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
