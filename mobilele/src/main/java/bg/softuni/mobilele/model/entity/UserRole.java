package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role name;

    public UserRole() {}

    public Role getName() {
        return name;
    }

    public UserRole setName(Role name) {
        this.name = name;
        return this;
    }
}
