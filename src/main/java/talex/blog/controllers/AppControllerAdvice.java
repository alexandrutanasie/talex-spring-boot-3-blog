package talex.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import talex.blog.components.AppComponent;
import talex.blog.entities.Category;
import talex.blog.entities.SiteUser;
import talex.blog.services.CategoryService;

@ControllerAdvice
public class AppControllerAdvice {
    @Autowired
    private AppComponent appComponent;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("currentUser")
    public SiteUser getCurrentUser() {
        return appComponent.getCurrentUser();
    }
    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}