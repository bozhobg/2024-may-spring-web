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
@Constraint(validatedBy = RoleIdValidator.class)
public @interface RoleIdValid {

    String message() default ErrorMessages.INVALID_SELECTION;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
