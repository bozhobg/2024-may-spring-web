package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.constants.ErrorMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class RegisterDTO {

//    TODO: validation with db
    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String username;

    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String password;

    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String firstName;

    @NotBlank(message = ErrorMessages.NOT_BLANK)
    @Size(min = 2, max = 20, message = ErrorMessages.LENGTH_BETWEEN_2_AND_20)
    private String lastName;

//    TODO: validation with db
    @NotNull(message = ErrorMessages.INVALID_SELECTION)
    private Long roleId;

    public RegisterDTO(){}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
