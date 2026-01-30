package com.example.terea.controller;

import com.example.terea.model.Terea;
import com.example.terea.service.TereaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tereas")
public class TereaController {

    private final TereaService service;

    public TereaController(TereaService service) {
        this.service = service;
    }

    @PostMapping
    public Terea create(@RequestBody Terea terea) {
        return service.create(terea);
    }

    @GetMapping
    public List<Terea> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.removeById(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Void> setAvailability(@PathVariable int id, @RequestParam boolean value) {
        return service.setAvailability(id, value) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


