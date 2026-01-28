package com.example.registration.repository;

import com.example.registration.model.Registr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrRepository extends JpaRepository<Registr, Integer> {
}
