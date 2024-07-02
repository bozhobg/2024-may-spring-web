package bg.softuni.mobilele.validation;

import bg.softuni.mobilele.constants.ErrorMessages;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ModelValidator.class)
public @interface ModelValid {

    String message() default ErrorMessages.INVALID_MODEL;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
