package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Task create(String title) {
        return repository.save(new Task(title));
    }

    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Task setDone(Long id, boolean done) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));

        task.setDone(done);

        // ВАЖНО: save можно даже не писать — JPA сам сохранит на commit (dirty checking)
        // но оставить можно, это не ошибка:
        // return repository.save(task);

        return task;
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public Task renameAndMarkDone(Long id, String newTitle, boolean done) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));

        task.setTitle(newTitle);   // если пустой — exception
        task.setDone(done);

        return task;
    }
}
