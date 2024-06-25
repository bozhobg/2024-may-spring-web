package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MoodEnum name;

    @Column
    private String description;

    public Mood() {}

    public Long getId() {
        return id;
    }

    public Mood setId(Long id) {
        this.id = id;
        return this;
    }

    public MoodEnum getName() {
        return name;
    }

    public Mood setName(MoodEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
