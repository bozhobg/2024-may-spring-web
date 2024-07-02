package bg.softuni.mobilele.validation;

import bg.softuni.mobilele.service.ModelService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelValidator implements ConstraintValidator<ModelValid, Long> {

    private final ModelService modelService;

    @Autowired
    public ModelValidator(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        return value != null && this.modelService.existsById(value);
    }
}
