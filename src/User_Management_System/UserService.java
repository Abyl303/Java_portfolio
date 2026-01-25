package User_Management_System;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserService {
    List<User> users=new ArrayList<>();
    private int nextUserId=1;

    public int generateId(){
        return nextUserId++;
    }
    public void addUser(User user){
        if(user==null){
            throw new IllegalArgumentException("Can't be empry!");
        }
        users.add(user);
    }
    public List<User> getAllUsers(){
        return new ArrayList<>(users);
    }
    public User findUserById(int id){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId()==id){
                return users.get(i);
            }
        }
        return null;
    }
    public User findUserByUsername(String username){
        for(int i=0; i<users.size(); i++){
            if(Objects.equals(users.get(i).getUsername(), username)){
                return users.get(i);
            }
        }
        return null;
    }
    public boolean removeUserById(int id){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId()==id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean deactivateUser(int id){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId()==id){
                users.get(i).setActive(false);
                return true;
            }
        }
        return false;
    }
    public boolean activateUser(int id){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId()==id){
                users.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }
    public List<User> getUsersByRole(String role){
        List<User> result=new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            if (users.get(i).getRole().equals(role)) {
                result.add(users.get(i));
            }
        }
        return result;
    }
    public List<User> getActiveUsers(){
        List<User> result2=new ArrayList<>();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).isActive()){
                result2.add(users.get(i));
            }
        }
        return result2;
    }

}
