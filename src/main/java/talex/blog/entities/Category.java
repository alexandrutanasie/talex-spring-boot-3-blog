package talex.blog.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Title is required")
    private String title;
    private String metaTitle;
    private String metaDescription;

    @Column(name = "content", columnDefinition = "TEXT", nullable = true)
    private String content;

    @NotEmpty(message = "Url is required")
    @Column(name = "url", unique = true)
    private String url;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "categories", cascade = { CascadeType.ALL })
    List<Post> posts = new ArrayList<>();
    @Override
    public String toString() {
        return "Category{id=" + id + ", title='" + title + "'}";
    }
}
