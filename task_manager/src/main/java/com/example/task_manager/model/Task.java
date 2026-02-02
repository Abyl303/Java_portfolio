package com.example.task_manager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    // можешь оставить nullable=false, если дедлайн обязателен
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // обязательно для JPA
    public Task() {}

    public Task(String title, String description, TaskStatus status, LocalDateTime deadline, User user) {
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setDeadline(deadline);
        setUser(user);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getDeadline() { return deadline; }
    public User getUser() { return user; }

    public void setTitle(String title){
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty!");
        }
        this.title = title.trim();
    }

    public void setDescription(String description){
        if(description == null){
            this.description = "";
            return;
        }
        if(description.length() > 1000){
            throw new IllegalArgumentException("Description is too long (max 1000)!");
        }
        this.description = description.trim();
    }

    public void setStatus(TaskStatus status){
        if(status == null){
            throw new IllegalArgumentException("Status can't be null!");
        }
        this.status = status;
    }

    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }

    public void setUser(User user){
        if(user == null){
            throw new IllegalArgumentException("User can't be null!");
        }
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", deadline=" + deadline +
                '}';
    }
}
