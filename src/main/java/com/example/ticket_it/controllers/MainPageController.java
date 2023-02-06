package com.example.ticket_it.controllers;

import com.example.ticket_it.services.MainPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        if (httpSession.getAttribute("isLoggedIn") != null) {
            model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
            model.addAttribute("user_name", httpSession.getAttribute("user_name"));
            model.addAttribute("user_surname", httpSession.getAttribute("user_surname"));
            model.addAttribute("user_login", httpSession.getAttribute("user_login"));
            model.addAttribute("user_bank_balance", httpSession.getAttribute("user_bank_balance"));
        } else {
            httpSession.setAttribute("isLoggedIn", false);
            model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        }
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

    @RequestMapping("/deposit")
    public String depositPage(Model model) {
        model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        model.addAttribute("user_name", httpSession.getAttribute("user_name"));
        model.addAttribute("user_surname", httpSession.getAttribute("user_surname"));
        model.addAttribute("user_login", httpSession.getAttribute("user_login"));
        model.addAttribute("user_bank_balance", httpSession.getAttribute("user_bank_balance"));
        return "deposit.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
