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
@Constraint(validatedBy = YearValidator.class)
public @interface YearValid {

    int before();
    int after();
//    TODO: format with before() and after()
    String message() default ErrorMessages.INVALID_YEAR;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
