package com.example.registration.controller;

import com.example.registration.model.Registr;
import com.example.registration.service.RegService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegController {

    private final RegService service;

    public RegController(RegService service) {
        this.service = service;
    }

    // READ - показать страницу со списком
    @GetMapping("/registrations")
    public String showPage(Model model) {
        model.addAttribute("users", service.getAll());
        return "registrations";
    }

    // CREATE - добавить пользователя
    @PostMapping("/registrations")
    public String addUser(@RequestParam String name,
                          @RequestParam Integer age,
                          @RequestParam String email) {

        System.out.println("POST пришел: " + name + " " + age + " " + email);
        service.addUser(new Registr(name, age, email));
        return "redirect:/registrations";
    }


    // DELETE - удалить по id
    @PostMapping("/registrations/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        service.removeUserById(id);
        return "redirect:/registrations";
    }
}
