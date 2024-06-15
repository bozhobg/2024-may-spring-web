package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.Role;

public class RoleDTO {
    private Long id;
    private Role name;

    public RoleDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }
}
