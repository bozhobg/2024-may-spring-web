package com.plannerapp.model.dto;

import com.plannerapp.constants.ErrorMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @NotBlank(message = ErrorMessages.USERNAME_BLANK)
    @Size(min = 3, max = 20, message = ErrorMessages.USERNAME_LENGTH)
    private String username;

    @NotBlank(message = ErrorMessages.PASSWORD_BLANK)
    @Size(min = 3, max = 20, message = ErrorMessages.PASSWORD_LENGTH)
    private String password;

    public UserLoginDTO() {}

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
