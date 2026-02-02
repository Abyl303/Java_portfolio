package com.example.task_manager.service;

import com.example.task_manager.model.Task;
import com.example.task_manager.model.TaskStatus;
import com.example.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Task create(Task task) {
        if (task == null) throw new IllegalArgumentException("Task can't be null");
        return repository.save(task);
    }

    // READ ALL
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // DELETE
    public boolean removeById(long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // UPDATE availability
    public boolean updateStatus(long id, TaskStatus status) {
        if (status == null) throw new IllegalArgumentException("Status can't be null");

        return repository.findById(id)
                .map(task -> {
                    task.setStatus(status);
                    repository.save(task);
                    return true;
                })
                .orElse(false);
    }

    // удобные методы
    public boolean updateStatusDone(long id) {
        return updateStatus(id, TaskStatus.DONE);
    }

    public boolean updateStatusToDo(long id) {
        return updateStatus(id, TaskStatus.TODO);
    }

    public boolean updateStatusInProgress(long id) {
        return updateStatus(id, TaskStatus.IN_PROGRESS);
    }
}
