package com.example.ticket_it.controllers;

import com.example.ticket_it.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpServletRequest request) {

        HttpSession session = request.getSession();
        loginService.loginUser(session, username, password);

        return "redirect:/";
    }
}
