package bg.softuni.pathfinder.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private boolean isAdmin;

    public void login(Long id, String username, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.isAdmin = false;
    }

    public boolean isLogged() {
        return this.id != null;
    }

    public Boolean isAdmin() {
//        Wrapper data type (null => not set)

        return this.isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
