package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.constants.ErrorMessages;
import jakarta.validation.constraints.*;

public class LoginDTO {

    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String username;

    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String password;

    public LoginDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
