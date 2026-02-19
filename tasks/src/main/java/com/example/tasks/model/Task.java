package com.example.tasks.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

    public Task(String title ){
        setTitle(title);
        this.done = false;
    }

    public Task(){}

    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public boolean isDone(){
        return done;
    }

    public void setTitle(String title){
        if(title==null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty!");
        }
        this.title=title;
    }
    public void setDone(boolean done){
        this.done=done;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Title: " + title +
                " | Status: " + done;
    }
}
