package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public Priority() {
        this.tasks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Priority setId(Long id) {
        this.id = id;
        return this;
    }

    public PriorityName getName() {
        return name;
    }

    public Priority setName(PriorityName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
