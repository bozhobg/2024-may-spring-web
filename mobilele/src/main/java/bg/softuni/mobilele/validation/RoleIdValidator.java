package bg.softuni.mobilele.validation;

import bg.softuni.mobilele.service.RoleService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleIdValidator implements ConstraintValidator<RoleIdValid, Long> {

    private final RoleService roleService;

    public RoleIdValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return this.roleService.isIdValid(value);
    }
}
