package bg.softuni.mobilele.validation;

import bg.softuni.mobilele.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    private final UserService userService;

    public UsernameUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return this.userService.isUsernameUnique(value);
    }
}
