package com.example.registration.model;


import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String email;

    public Registr() {}

    public Registr(String name,Integer age,String email){
        setName(name);
        setAge(age);
        setEmail(email);
    }

    public Integer getId(){ return id; }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }

    public void setName(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name can't be empty!");
        }
        this.name=name;
    }
    public void setAge(Integer age){
        if(age<=15){
            throw new IllegalArgumentException("Age must be greater than 15!");
        }
        this.age=age;
    }
    public void setEmail(String email){
        if(email==null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email can't be empty1");
        }
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email must contain @");
        }
        this.email=email;
    }

    @Override
    public String toString(){
        return "ID: "+id+". Name: "+name+". Age: "+age+". Email: "+email;
    }


}
