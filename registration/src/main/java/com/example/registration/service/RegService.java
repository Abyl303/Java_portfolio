package com.example.registration.service;

import com.example.registration.model.Registr;
import com.example.registration.repository.RegistrRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegService {

    private final RegistrRepository repository;

    public RegService(RegistrRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Registr addUser(Registr registr) {
        if (registr == null) {
            throw new IllegalArgumentException("registr can't be empty");
        }
        return repository.save(registr);
    }

    // DELETE
    public void removeUserById(int id) {
        repository.deleteById(id);
    }

    // READ
    public List<Registr> getAll() {
        return repository.findAll();
    }
}
