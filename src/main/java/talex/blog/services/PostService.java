package talex.blog.services;

import java.util.List;
import java.util.Optional;

import talex.blog.entities.Post;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> findById(Long Id);
    Optional<Post> findByUrl(String url);
    void savePost(Post post);
    void deleteById(Long Id);
    List<Post> findTop6ByOrderByCreatedAtDesc();
}
