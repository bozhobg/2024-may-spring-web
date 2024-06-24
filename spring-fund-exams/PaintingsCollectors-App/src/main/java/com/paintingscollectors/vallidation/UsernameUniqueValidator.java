package com.paintingscollectors.vallidation;

import com.paintingscollectors.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    private final UserService userService;

    @Autowired
    public UsernameUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        TODO: on register with new username renders error

        return this.userService.isUsernameUnique(value);
    }
}
