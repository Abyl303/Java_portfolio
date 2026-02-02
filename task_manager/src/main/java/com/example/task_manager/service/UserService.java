package com.example.task_manager.service;


import com.example.task_manager.model.Role;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public User create(User user) {
        if (user == null) throw new IllegalArgumentException("User can't be null");
        return repository.save(user);
    }

    // READ ALL
    public List<User> getAll() {
        return repository.findAll();
    }

    // DELETE
    public boolean removeById(long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // UPDATE role
    public boolean updateRoleAdmin(long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setRole(Role.ADMIN);
                    repository.save(user);
                    return true;
                })
                .orElse(false);
    }
}
