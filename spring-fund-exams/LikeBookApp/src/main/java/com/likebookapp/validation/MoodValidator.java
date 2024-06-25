package com.likebookapp.validation;

import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.service.MoodService;
import com.likebookapp.validation.annotations.MoodValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class MoodValidator implements ConstraintValidator<MoodValid, MoodEnum> {

    private final MoodService moodService;

    @Autowired
    public MoodValidator(MoodService moodService) {
        this.moodService = moodService;
    }

    @Override
    public boolean isValid(MoodEnum value, ConstraintValidatorContext context) {
//        TODO: do with string and check so that Enum.valueOf(value) doesn't throw
        return this.moodService.isMoodValid(value);
    }
}
