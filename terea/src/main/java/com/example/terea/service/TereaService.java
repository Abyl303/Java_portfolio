package com.example.terea.service;

import com.example.terea.model.Terea;
import com.example.terea.repository.TereaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TereaService {

    private final TereaRepository repository;

    public TereaService(TereaRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Terea create(Terea terea) {
        if (terea == null) throw new IllegalArgumentException("Terea can't be null");
        return repository.save(terea);
    }

    // READ ALL
    public List<Terea> getAll() {
        return repository.findAll();
    }

    // DELETE
    public boolean removeById(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    // UPDATE availability
    public boolean setAvailability(int id, boolean available) {
        return repository.findById(id).map(t -> {
            t.setAvailable(available);
            repository.save(t);
            return true;
        }).orElse(false);
    }
}
