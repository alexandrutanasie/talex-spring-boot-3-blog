package talex.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import talex.blog.entities.Category;
import talex.blog.entities.Post;
import talex.blog.services.CategoryService;
import talex.blog.services.PostService;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;



@Controller
public class FrontendController {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> latestPosts = postService.findTop6ByOrderByCreatedAtDesc();
        
        model.addAttribute("title", "Homepage");
        model.addAttribute("latestPosts", latestPosts);
        return "index";
    }

    @GetMapping("/category/{url}")
    public String getCategoryPage(@PathVariable("url") String url, Model model) {
        Optional<Category> _category = categoryService.findByUrl(url);
        if(_category.isPresent()){
           Category category = _category.get();
            model.addAttribute("category", category);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "category";
    }

    @GetMapping("/post/{url}")
    public String getPostPage(@PathVariable("url") String url, Model model) {
        Optional<Post> _post = postService.findByUrl(url);
        if(_post.isPresent()){
           Post post = _post.get();
            model.addAttribute("post", post);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "post";
    }
    
}
