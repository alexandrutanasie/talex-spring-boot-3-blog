package talex.blog.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import talex.blog.entities.Post;
import talex.blog.repositories.PostRepository;
import talex.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void deleteById(Long Id){
        postRepository.deleteById(Id);
    }

    public void savePost(Post post){
        postRepository.save(post);
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public Optional<Post> findByUrl(String url){
        return postRepository.findByUrl(url);
    }

    public List<Post> findTop6ByOrderByCreatedAtDesc(){
        return postRepository.findTop6ByOrderByCreatedAtDesc();
    }
}
