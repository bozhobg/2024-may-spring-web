package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.constants.ErrorMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    private String username;

    @NotBlank(message = ErrorMessages.FIELD_NOT_BLANK)
    @Size(min = 2, message = ErrorMessages.LENGTH_MIN_TWO)
    private String password;

    public UserLoginDTO() {}

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
