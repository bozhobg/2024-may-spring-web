package com.plannerapp.service;

import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.dto.TaskInfoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskService {
    boolean addTask(TaskAddDTO data);

    @Transactional
    List<TaskInfoDTO> getUserTasks(Long userId);

    List<TaskInfoDTO> getUnassignedTasks();

    @Transactional
    void assignTask(Long taskId);

    void returnTask(Long taskId);

    void delete(Long taskId);
}
