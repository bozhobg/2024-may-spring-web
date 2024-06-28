package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.UserRole;
import bg.softuni.mobilele.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role role);
}
