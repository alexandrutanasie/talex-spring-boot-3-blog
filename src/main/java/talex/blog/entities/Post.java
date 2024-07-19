package talex.blog.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import talex.blog.components.AppComponent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Post {    
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

    @ManyToOne
    private SiteUser createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories = new ArrayList<>();

    public String getShortDescription(){
        String shortDescription = this.content;
               shortDescription = AppComponent.stripHtmlTags(shortDescription);
               shortDescription = AppComponent.truncateText(shortDescription, 200);

        return shortDescription;
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", title='" + title + "'}";
    }
}
