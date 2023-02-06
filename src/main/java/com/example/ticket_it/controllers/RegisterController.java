package com.example.ticket_it.controllers;

import com.example.ticket_it.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public String register(@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password) {

        registerService.addUser(name, surname, username, password);

        return "redirect:/";
    }
}
