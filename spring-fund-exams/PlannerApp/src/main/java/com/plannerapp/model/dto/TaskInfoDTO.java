package com.plannerapp.model.dto;

import com.plannerapp.model.enums.PriorityName;

import java.time.LocalDate;

public class TaskInfoDTO {
    private Long id;

    private String description;

    private LocalDate dueDate;

    private PriorityName priorityName;

    private Long userId;

    public TaskInfoDTO() {}

    public Long getId() {
        return id;
    }

    public TaskInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskInfoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskInfoDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public TaskInfoDTO setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public TaskInfoDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
