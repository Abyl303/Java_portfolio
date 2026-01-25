package User_Management_System;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;        // "ADMIN", "MANAGER", "USER"
    private boolean active;
    private String createdAt;

    public User(int id,String username,String email,String password,String role,boolean active,String createdAt){
        this.id=id;
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setActive(active);
        setCreatedAt(createdAt);
    }

    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public boolean isActive(){
        return active;
    }
    public String getCreatedAt(){
        return createdAt;
    }

    public void setUsername(String username){
        if(username==null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username can't be empty.");
        }
        this.username=username;
    }
    public void setEmail(String email){
        if(email==null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email can't be empty.");
        }
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email must contain '@'.");
        }
        this.email=email;
    }
    public void setPassword(String password){
        if(password==null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password can't be empty.");
        }
        if(password.length() < 6){
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
        this.password=password;
    }
    public void setRole(String role){
        if(role == null || role.trim().isEmpty()){
            throw new IllegalArgumentException("Role can't be empty.");
        }

        String r = role.trim().toUpperCase();
        if(!r.equals("ADMIN") && !r.equals("MANAGER") && !r.equals("USER")){
            throw new IllegalArgumentException("Role must be ADMIN, MANAGER or USER.");
        }

        this.role = r;
    }
    public void setActive(boolean active){
        this.active=active;
    }
    public void setCreatedAt(String createdAt){
        if(createdAt==null || createdAt.trim().isEmpty()){
            throw new IllegalArgumentException("Created at can't be empty.");
        }
        this.createdAt=createdAt;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
