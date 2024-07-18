package bg.softuni.pathfinder.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User {
//    TODO: 2nd approach: impl of user helper service to provide additional info for UserDetails

    private Long id;
    private String fullName;
    private String email;

    public AppUserDetails(
            Long id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String fullName,
            String email
    ) {
        super(username, password, authorities);
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
