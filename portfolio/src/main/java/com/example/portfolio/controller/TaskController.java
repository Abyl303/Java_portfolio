package com.example.portfolio.controller;

import com.example.portfolio.model.Task;
import com.example.portfolio.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam(defaultValue = "false") boolean status) {
        service.addTask(new Task(title, description, status));
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/activate")
    public String activate(@PathVariable int id) {
        service.setStatusById(id, true);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/deactivate")
    public String deactivate(@PathVariable int id) {
        service.setStatusById(id, false);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable int id) {
        service.removeTaskById(id);
        return "redirect:/";
    }
}
