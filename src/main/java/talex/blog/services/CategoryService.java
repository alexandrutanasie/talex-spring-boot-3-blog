package talex.blog.services;

import java.util.List;
import java.util.Optional;

import talex.blog.entities.Category;

public interface CategoryService {
    List<Category> getAllCategories();

    void saveCategory(Category category);
    void deleteById(Long Id);

    Optional<Category> findById(Long Id);
    Optional<Category> findByUrl(String url);
}
