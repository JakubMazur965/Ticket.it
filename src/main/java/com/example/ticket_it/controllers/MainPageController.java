package com.example.ticket_it.controllers;

import com.example.ticket_it.services.MainPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    @Autowired
    MainPageService mainPageService;
    @Autowired
    HttpSession httpSession;

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("events", MainPageService.getEvents());
        return "home.html";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login.html";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        return "register.html";
    }

    @RequestMapping("/payment_history")
    public String paymentHistoryPage(Model model) {
        return "payment_history.html";
    }

    @RequestMapping("/ticket_history")
    public String ticketHistoryPage(Model model) {
        return "ticket_history.html";
    }

    @RequestMapping("/deposit")
    public String depositPage(Model model) {
        model.addAttribute("bankBalance", httpSession.getAttribute("user_bank_balance"));
        return "deposit.html";
    }
}
