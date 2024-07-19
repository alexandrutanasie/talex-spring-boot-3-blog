package talex.blog.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class SiteUser
{

    // Define the validation group
    public interface PasswordValidationGroup {

    }

    
    // Define the validation group
    public interface ProfileValidationGroup {

    }
    // Define the validation group
    public interface EmailValidationGroup {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, name = "first_name")
    @NotEmpty(message = "Firstname is required", groups=ProfileValidationGroup.class)
    private String firstName;

    @Column(nullable=false, name = "last_name")
    @NotEmpty(message = "Lastname is required", groups=ProfileValidationGroup.class)
    private String lastName;

    @Column(nullable=false, unique=true)
    @NotEmpty(message = "Email is required", groups = EmailValidationGroup.class)
    private String email;

    @Column(nullable=false)
    @NotEmpty(message = "Password is required", groups=PasswordValidationGroup.class)
    private String password;
    
    @Transient
    @NotBlank(message = "Confirm password is required", groups=PasswordValidationGroup.class)
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @AssertTrue(message = "Passwords do not match", groups = PasswordValidationGroup.class)
    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword);
    }

    public String getDisplayName(){
        return this.firstName + " " + this.lastName;
    }
}