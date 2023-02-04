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
        model.addAttribute("event", eventService.getEventByID(id));
        model.addAttribute("ticketsSector1", eventService.getTicketsBySector(id, 1));
        model.addAttribute("ticketsSector2", eventService.getTicketsBySector(id, 2));
        model.addAttribute("ticketsSector3", eventService.getTicketsBySector(id, 3));
        model.addAttribute("ticketsSector4", eventService.getTicketsBySector(id, 4));
        model.addAttribute("seatsSector1", eventService.getSeatBySector(1));
        model.addAttribute("seatsSector2", eventService.getSeatBySector(2));
        model.addAttribute("seatsSector3", eventService.getSeatBySector(3));
        model.addAttribute("seatsSector4", eventService.getSeatBySector(4));
        return "event_tickets.html";
    }
}
