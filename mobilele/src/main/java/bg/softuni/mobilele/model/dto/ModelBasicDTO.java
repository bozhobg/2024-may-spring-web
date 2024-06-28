package bg.softuni.mobilele.model.dto;

public class ModelBasicDTO {

    private Long id;

    private String name;

    public ModelBasicDTO() {}

    public Long getId() {
        return id;
    }

    public ModelBasicDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelBasicDTO setName(String name) {
        this.name = name;
        return this;
    }
}
