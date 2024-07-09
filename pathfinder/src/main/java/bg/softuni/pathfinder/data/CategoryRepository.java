package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findAllByNameIn(List<CategoryType> cats);
}
