package com.bonappetit.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private Long id;

    private String username;

    public void login(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
}
