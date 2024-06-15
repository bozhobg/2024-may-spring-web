package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
}
