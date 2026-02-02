package com.example.task_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // ❗ обязательно для JPA
    public User() {}

    // основной конструктор (без id)
    public User(String username, String email, String passwordHash, Role role) {
        setUsername(username);
        setEmail(email);
        setPasswordHash(passwordHash);
        setRole(role);
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }

    public void setUsername(String username){
        if(username==null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username can't be empty");
        this.username=username.trim();
    }

    public void setEmail(String email){
        if(email==null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email");
        this.email=email.trim();
    }

    public void setPasswordHash(String passwordHash){
        if(passwordHash==null || passwordHash.isBlank())
            throw new IllegalArgumentException("Password hash can't be empty");
        this.passwordHash=passwordHash;
    }

    public void setRole(Role role){
        if(role==null)
            throw new IllegalArgumentException("Role can't be null");
        this.role=role;
    }

    @Override
    public String toString(){
        return "User{id=" + id + ", username='" + username + "', role=" + role + "}";
    }
}
