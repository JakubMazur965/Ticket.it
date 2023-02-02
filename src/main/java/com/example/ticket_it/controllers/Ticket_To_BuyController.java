package com.example.ticket_it.controllers;

import com.example.ticket_it.services.EventService;
import com.example.ticket_it.services.Ticket_To_BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Ticket_To_BuyController {
    @Autowired
    EventService eventService;
    Ticket_To_BuyService ticket_to_buyService;

    @GetMapping("/ticket/{id}")
    public String showTicketDetails(@PathVariable int id, Model model) {
        //model.addAttribute("event", eventService.findById(id)); <-- tu musi byc inne id
        // to samo dla ticket -> get Ticket by ID
        return "event_tickets.html";
    }
}
