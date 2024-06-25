package com.dictionaryapp.config;

import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private long id;
    private String username;

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public boolean isLogged() {
        return id != 0;
    }

    public void logout() {
        this.id = 0;
        this.username = null;
    }
}
