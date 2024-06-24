package com.paintingscollectors.vallidation;

import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.service.StyleService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class StyleValidator implements ConstraintValidator<StyleValid, StyleName> {

    private final StyleService styleService;

    @Autowired
    public StyleValidator(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public boolean isValid(StyleName value, ConstraintValidatorContext context) {
        return this.styleService.isValid(value);
    }
}
