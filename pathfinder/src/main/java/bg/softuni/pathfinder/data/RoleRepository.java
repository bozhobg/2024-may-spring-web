package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(UserRole role);
}
