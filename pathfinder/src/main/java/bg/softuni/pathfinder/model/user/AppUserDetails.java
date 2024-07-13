package bg.softuni.pathfinder.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User {

    private String fullName;
    private String email;

    public AppUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String fullName,
            String email
    ) {
        super(username, password, authorities);
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public AppUserDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUserDetails setEmail(String email) {
        this.email = email;
        return this;
    }
}
