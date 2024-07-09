package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.enums.CategoryType;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Set<Category> findByCategoryTypes(List<CategoryType> cats);
}
