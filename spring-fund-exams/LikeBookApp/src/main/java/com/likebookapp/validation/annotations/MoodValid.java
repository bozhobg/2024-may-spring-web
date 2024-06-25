package com.likebookapp.validation.annotations;

import com.likebookapp.validation.MoodValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoodValidator.class)
public @interface MoodValid {

    String message() default "Invalid mood!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
