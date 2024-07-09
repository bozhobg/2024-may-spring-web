package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.data.CategoryRepository;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findByCategoryTypes(List<CategoryType> cats) {

        return this.categoryRepository.findAllByNameIn(cats);
    }
}
