package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.constants.ErrorMessages;
import bg.softuni.pathfinder.validation.EmailValid;
import bg.softuni.pathfinder.validation.UsernameValid;
import jakarta.validation.constraints.*;

public class UserRegisterDTO {

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    @UsernameValid(message = ErrorMessages.USERNAME_INVALID)
    private String username;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    private String fullName;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Email(message = ErrorMessages.EMAIL_FORMAT)
    @EmailValid(message = ErrorMessages.EMAIL_INVALID)
    private String email;

    @NotNull(message = ErrorMessages.FIELD_NOT_BLANK)
    @PositiveOrZero(message = ErrorMessages.FIELD_POSITIVE_OR_ZERO)
    @Max(value = 90, message = "Age must be below 90!")
    private Integer age;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    private String password;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    private String confirmPassword;

    public UserRegisterDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
