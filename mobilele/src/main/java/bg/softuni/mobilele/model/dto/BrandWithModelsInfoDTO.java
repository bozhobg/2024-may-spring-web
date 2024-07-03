package bg.softuni.mobilele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandWithModelsInfoDTO {

    private String name;

    private List<ModelInfoDTO> models;

    public BrandWithModelsInfoDTO() {
        this.models = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public BrandWithModelsInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelInfoDTO> getModels() {
        return models;
    }

    public BrandWithModelsInfoDTO setModels(List<ModelInfoDTO> models) {
        this.models = models;
        return this;
    }
}
