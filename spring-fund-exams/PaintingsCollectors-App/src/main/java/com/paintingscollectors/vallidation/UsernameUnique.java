package com.paintingscollectors.vallidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameUniqueValidator.class)
public @interface UsernameUnique {

    String message() default "Username invalid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };}
