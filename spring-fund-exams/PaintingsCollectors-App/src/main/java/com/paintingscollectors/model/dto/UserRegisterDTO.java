package com.paintingscollectors.model.dto;

import com.paintingscollectors.constants.ErrorMessages;
import com.paintingscollectors.vallidation.EmailUnique;
import com.paintingscollectors.vallidation.UsernameUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {

    @NotBlank(message = ErrorMessages.USERNAME_BLANK)
    @Size(min = 3, max = 20, message = ErrorMessages.USERNAME_LENGTH)
    @UsernameUnique
    private String username;

    @NotBlank(message = ErrorMessages.EMAIL_BLANK)
    @Email(message = ErrorMessages.EMAIL_FORMAT)
    @EmailUnique
    private String email;

    @NotBlank(message = ErrorMessages.PASSWORD_BLANK)
    @Size(min = 3, max = 20, message = ErrorMessages.PASSWORD_LENGTH)
    private String password;

    @NotBlank(message = ErrorMessages.PASSWORD_BLANK)
    @Size(min = 3, max = 20, message = ErrorMessages.PASSWORD_LENGTH)
    private String confirmPassword;

    public UserRegisterDTO() {}

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
