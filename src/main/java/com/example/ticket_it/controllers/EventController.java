package com.example.ticket_it.controllers;

import com.example.ticket_it.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("/event/{id}")
    public String showEventDetails(@PathVariable int id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        model.addAttribute("tickets", eventService.getAllTickets());
        return "ticket_to_buy.html";
    }
}
