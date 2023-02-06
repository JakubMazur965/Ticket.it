package com.example.ticket_it.controllers;

import com.example.ticket_it.components.Event;
import com.example.ticket_it.components.Seat;
import com.example.ticket_it.components.Ticket;
import com.example.ticket_it.services.TicketHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TicketHistoryController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    TicketHistoryService ticketHistoryService;

    @RequestMapping("/ticket_history")
    public String ticketHistory(Model model) {
        List<Seat> seats = ticketHistoryService.getSeatsHistory();
        List<Event> events = ticketHistoryService.getEventsHistory();
        List<Ticket> tickets = ticketHistoryService.getTicketsHistory();

        model.addAttribute("seats", seats);
        model.addAttribute("events", events);
        model.addAttribute("tickets", tickets);
        model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        model.addAttribute("user_name", httpSession.getAttribute("user_name"));
        model.addAttribute("user_surname", httpSession.getAttribute("user_surname"));
        model.addAttribute("user_login", httpSession.getAttribute("user_login"));
        model.addAttribute("user_bank_balance", httpSession.getAttribute("user_bank_balance"));
        return "ticket_history.html";
    }
}
