package com.example.task_manager.service;

import com.example.task_manager.dto.LoginRequest;
import com.example.task_manager.dto.RegisterRequest;
import com.example.task_manager.model.Role;
import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;
import com.example.task_manager.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository users;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository users,
                       BCryptPasswordEncoder encoder,
                       JwtService jwtService) {
        this.users = users;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    // REGISTER (создать пользователя)
    public User register(RegisterRequest req) {
        if (req == null) throw new IllegalArgumentException("Request can't be null");
        if (req.username == null || req.username.trim().isEmpty())
            throw new IllegalArgumentException("Username required");
        if (req.email == null || !req.email.contains("@"))
            throw new IllegalArgumentException("Email invalid");
        if (req.password == null || req.password.length() < 6)
            throw new IllegalArgumentException("Password min 6 chars");

        String username = req.username.trim();
        String email = req.email.trim();

        if (users.existsByUsername(username))
            throw new IllegalArgumentException("Username already exists");
        if (users.existsByEmail(email))
            throw new IllegalArgumentException("Email already exists");

        String hash = encoder.encode(req.password);

        User user = new User(username, email, hash, Role.USER);
        return users.save(user);
    }

    // LOGIN (вернуть JWT)
    public String login(LoginRequest req) {
        if (req == null) throw new IllegalArgumentException("Request can't be null");
        if (req.username == null || req.username.trim().isEmpty())
            throw new IllegalArgumentException("Username required");
        if (req.password == null || req.password.isEmpty())
            throw new IllegalArgumentException("Password required");

        User user = users.findByUsername(req.username.trim())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        boolean ok = encoder.matches(req.password, user.getPasswordHash());
        if (!ok) throw new IllegalArgumentException("Invalid username or password");

        return jwtService.generateToken(user.getUsername(), user.getRole().name());
    }
}
