package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.entity.UserRole;
import bg.softuni.mobilele.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class RegisterDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @Size(min = 2, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    private Role role;

    public RegisterDTO(){}

    public @NotBlank @Size(min = 5, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 5, max = 20) String username) {
        this.username = username;
    }

    public @NotBlank @Size(min = 5, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 5, max = 20) String password) {
        this.password = password;
    }

    public @NotBlank @Size(min = 5, max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(min = 5, max = 20) String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank @Size(min = 5, max = 20) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank @Size(min = 5, max = 20) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull Role getRole() {
        return role;
    }

    public void setRole(@NotNull Role role) {
        this.role = role;
    }
}
