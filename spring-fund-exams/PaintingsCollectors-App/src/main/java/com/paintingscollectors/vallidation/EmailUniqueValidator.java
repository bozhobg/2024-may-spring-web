package com.paintingscollectors.vallidation;

import com.paintingscollectors.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    private final UserService userService;

    @Autowired
    public EmailUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.isEmailUnique(value);
    }
}
