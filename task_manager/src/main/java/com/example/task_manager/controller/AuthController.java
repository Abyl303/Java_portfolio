package com.example.task_manager.controller;

import com.example.task_manager.dto.AuthResponse;
import com.example.task_manager.dto.LoginRequest;
import com.example.task_manager.dto.LoginResponse;
import com.example.task_manager.dto.RegisterRequest;
import com.example.task_manager.model.User;
import com.example.task_manager.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return auth.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        String token = auth.login(req);
        return new LoginResponse(token);
    }

}
