package talex.blog.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private SiteUser siteUser;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // Corectăm metoda setUser
    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
    // Corectăm metoda setUser
    public SiteUser getSiteUser() {
        return this.siteUser;
    }
}