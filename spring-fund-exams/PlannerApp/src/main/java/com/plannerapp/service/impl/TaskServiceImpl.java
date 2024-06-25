package com.plannerapp.service.impl;

import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.dto.TaskInfoDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final UserSession userSession;

    @Autowired
    public TaskServiceImpl(
            TaskRepository taskRepository,
            UserRepository userRepository,
            PriorityRepository priorityRepository,
            UserSession userSession
    ) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }


    @Override
    public boolean addTask(TaskAddDTO data) {
        Optional<Priority> priorityByName = this.priorityRepository.findByName(data.getPriorityName());

        if (priorityByName.isEmpty()) return false;

        this.taskRepository.save(
                new Task()
                        .setDescription(data.getDescription())
                        .setDueDate(data.getDueDate())
                        .setPriority(priorityByName.get())
        );

        return true;
    }

    @Override
    public List<TaskInfoDTO> getUserTasks(Long userId) {

        return this.userRepository.findById(userId)
                .map(User::getAssignedTasks)
                .orElse(new ArrayList<>())
                .stream()
                .map(this::mapToTaskInfoDTO)
                .toList();
    }

    @Override
    public List<TaskInfoDTO> getUnassignedTasks() {

        return this.taskRepository.findAllByUserIsNull()
                .stream()
                .map(this::mapToTaskInfoDTO)
                .toList();
    }

    @Override
    public void assignTask(Long taskId) {
        User user = this.userRepository.findById(userSession.getId()).orElse(null);
        Task task = this.taskRepository.findById(taskId).orElse(null);

        if (user == null || task == null) return;

        task.setUser(user);
        this.taskRepository.save(task);
    }

    @Override
    public void returnTask(Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);

        if (task == null) return;

        task.setUser(null);
        this.taskRepository.save(task);
    }

    @Override
    public void delete(Long taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);

        if (task == null) return;

        this.taskRepository.delete(task);
    }

    private TaskInfoDTO mapToTaskInfoDTO(Task task) {

        return new TaskInfoDTO()
                .setId(task.getId())
                .setDescription(task.getDescription())
                .setPriorityName(task.getPriority().getName())
                .setDueDate(task.getDueDate());
    }
}
