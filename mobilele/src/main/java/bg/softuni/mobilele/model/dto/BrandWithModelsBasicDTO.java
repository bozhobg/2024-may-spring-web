package bg.softuni.mobilele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandWithModelsBasicDTO {

    private Long id;

    private String name;

    private List<ModelBasicDTO> models;

    public BrandWithModelsBasicDTO() {
        this.models = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public BrandWithModelsBasicDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandWithModelsBasicDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelBasicDTO> getModels() {
        return models;
    }

    public BrandWithModelsBasicDTO setModels(List<ModelBasicDTO> models) {
        this.models = models;
        return this;
    }
}
