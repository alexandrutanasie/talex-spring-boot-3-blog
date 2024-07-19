package talex.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import talex.blog.components.AppComponent;
import talex.blog.entities.Category;
import talex.blog.entities.Post;
import talex.blog.services.CategoryService;
import talex.blog.services.PostService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private AppComponent appComponent;
    
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    
    @GetMapping("/")
    public String getDashboard() {
        return "dashboard";
    }
    
    @GetMapping("/category/list")
    public String getCategoriesList(Model model) {
        List<Category> categories = categoryService.getAllCategories();    

        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @GetMapping("/category/add")
    public String getCategoriesAdd(Model model) {
        Category category = new Category();    

        model.addAttribute("category", category);
        return "admin/category/form";
    }

    @GetMapping("/category/edit/{id}")
    public String getCategoriesEdit(@PathVariable("id") Long Id, Model model) {
        Optional<Category> category = categoryService.findById(Id);
        if (category.isPresent()) {
            model.addAttribute("category", category);
            return "admin/category/form";
        } else {
            // Nu a fost găsită nicio vacanță cu ID-ul specificat, returnăm un răspuns bad request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Catgory not found");
        }
    }

    @PostMapping("/category/save")
    public String postCategorySave(@Valid Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {   

        if(result.hasErrors()){
            model.addAttribute("category", category);
            return "admin/category/add";
        }

        categoryService.saveCategory(category);

        return "redirect:/admin/category/list";       
    }

    @GetMapping("/category/delete/{id}")
    public String getCategoryDelete(@PathVariable("id") Long Id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(Id);

        redirectAttributes.addFlashAttribute("success", "Category successfully deleted");
        return "redirect:/admin/category/list";
    }

    @GetMapping("/post/list")
    public String getPostsList(Model model) {
        List<Post> posts = postService.getAllPosts();    

        model.addAttribute("posts", posts);
        return "admin/post/list";
    }
    
    @GetMapping("/post/add")
    public String getPostAdd(Model model) {
        Post post = new Post();    
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("post", post);
        return "admin/post/form";
    }

    @GetMapping("/post/edit/{id}")
    public String getPostEdit(@PathVariable("id") Long Id, Model model) {
        Optional<Post> post = postService.findById(Id);
        if (post.isPresent()) {
            List<Category> allCategories = categoryService.getAllCategories();
            model.addAttribute("allCategories", allCategories);
            model.addAttribute("post", post);
            return "admin/post/form";
        } else {
            // Nu a fost găsită nicio vacanță cu ID-ul specificat, returnăm un răspuns bad request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post not found");
        }
    }

    @PostMapping("/post/save")
    public String postPostSave(@Valid Post post, BindingResult result, Model model, RedirectAttributes redirectAttributes) {   

        if(result.hasErrors()){
            List<Category> allCategories = categoryService.getAllCategories();
            model.addAttribute("allCategories", allCategories);
            model.addAttribute("post", post);
            return "admin/post/add";
        }

        post.setCreatedBy(appComponent.getCurrentUser());
        postService.savePost(post);

        return "redirect:/admin/post/list";       
    }

    @GetMapping("/post/delete/{id}")
    public String getPostDelete(@PathVariable("id") Long Id, RedirectAttributes redirectAttributes) {
        postService.deleteById(Id);

        redirectAttributes.addFlashAttribute("success", "Post successfully deleted");
        return "redirect:/admin/post/list";
    }
}
