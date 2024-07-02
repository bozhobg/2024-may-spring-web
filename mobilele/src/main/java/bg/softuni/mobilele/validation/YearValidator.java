package bg.softuni.mobilele.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidator implements ConstraintValidator<YearValid, Integer> {

    private int before;
    private int after;

    @Override
    public void initialize(YearValid constraintAnnotation) {
        this.before = constraintAnnotation.before();
        this.after = constraintAnnotation.after();

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && this.after <= value && value <= this.before;
    }
}
