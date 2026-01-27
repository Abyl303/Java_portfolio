package com.example.portfolio.service;

import com.example.portfolio.model.Task;
import com.example.portfolio.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Task can't be empty");
        return repository.save(task);
    }

    public void removeTaskById(int id) {
        repository.deleteById(id);
    }

    public void setStatusById(int id, boolean status) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        task.setStatus(status);
        repository.save(task);
    }
}
