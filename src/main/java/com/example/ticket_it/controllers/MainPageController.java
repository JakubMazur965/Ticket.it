package com.example.ticket_it.controllers;

import com.example.ticket_it.services.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    @Autowired
    MainPageService mainPageService;

    @RequestMapping("/")
    public String mainPage(Model model) {
        return "base.html";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login.html";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        return "register.html";
    }
}
