package com.plannerapp.model.dto;

import com.plannerapp.constants.ErrorMessages;
import com.plannerapp.model.enums.PriorityName;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskAddDTO {

    @NotBlank(message = ErrorMessages.DESC_BLANK)
    @Size(min = 2, max = 50, message = ErrorMessages.DESC_LENGTH)
    private String description;

    @NotNull(message = ErrorMessages.DATE_BLANK)
    @Future(message = ErrorMessages.DATE_FUTURE)
    private LocalDate dueDate;

    @NotNull(message = ErrorMessages.PRIORITY_BLANK)
    private PriorityName priorityName;

    public TaskAddDTO() {}

    public String getDescription() {
        return description;
    }

    public TaskAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public TaskAddDTO setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
        return this;
    }
}
