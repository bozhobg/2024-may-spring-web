package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Category;

import java.time.Year;

public class ModelInfoDTO {

    private String name;

    private Category category;

    private Year startYear;

    private Year endYear;

    private String imageUrl;

    public ModelInfoDTO() {}

    public String getName() {
        return name;
    }

    public ModelInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ModelInfoDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Year getStartYear() {
        return startYear;
    }

    public ModelInfoDTO setStartYear(Year startYear) {
        this.startYear = startYear;
        return this;
    }

    public Year getEndYear() {
        return endYear;
    }

    public ModelInfoDTO setEndYear(Year endYear) {
        this.endYear = endYear;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelInfoDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
