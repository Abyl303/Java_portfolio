package com.example.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status;

    public Task() {}

    public Task(String title, String description, boolean status) {
        setTitle(title);
        setDescription(description);
        this.status = status;
    }

    public Integer getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty");
        }
        this.title = title.trim();
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be empty");
        }
        this.description = description.trim();
    }

    public boolean isStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }
}
