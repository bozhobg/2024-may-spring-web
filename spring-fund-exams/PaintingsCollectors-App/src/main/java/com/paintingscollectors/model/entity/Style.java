package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleName name;

    @Column(nullable = false)
    private String description;

    public Style() {}

    public Long getId() {
        return id;
    }

    public Style setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleName getName() {
        return name;
    }

    public Style setName(StyleName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
