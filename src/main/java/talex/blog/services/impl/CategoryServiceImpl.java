package talex.blog.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talex.blog.entities.Category;
import talex.blog.repositories.CategoryRepository;
import talex.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public Optional<Category> findById(Long Id){
        return categoryRepository.findById(Id);
    }

    public Optional<Category> findByUrl(String url){
        return categoryRepository.findByUrl(url);
    }

    public void deleteById(Long Id){
        categoryRepository.deleteById(Id);
    }
}
