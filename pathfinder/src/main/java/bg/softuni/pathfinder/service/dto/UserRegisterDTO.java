package bg.softuni.pathfinder.service.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullname;

    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotBlank
    @Size(min = 5, max = 20)
    private String confirmPassword;

    public UserRegisterDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
