package com.example.ticket_it.controllers;

import com.example.ticket_it.components.Ticket_To_Buy;
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

    @Autowired
    Ticket_To_BuyService ticket_to_buyService;

    @GetMapping("/ticket/{id}")
    public String showTicketDetails(@PathVariable int id, Model model) {
        Ticket_To_Buy ticket = ticket_to_buyService.getTicketByID(id);

        model.addAttribute("event", eventService.getEventByID(ticket.getEventID()));
        model.addAttribute("ticket", ticket);
        model.addAttribute("seat", ticket_to_buyService.getSeatByID(ticket.getSeatID()));

        return "ticket_to_buy.html";
    }
}
