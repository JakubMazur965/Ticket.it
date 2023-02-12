package com.example.ticket_it.controllers;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Ticket_To_Buy;
import com.example.ticket_it.services.EventService;
import com.example.ticket_it.services.Ticket_To_BuyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Ticket_To_BuyController {
    @Autowired
    EventService eventService;

    @Autowired
    Ticket_To_BuyService ticket_to_buyService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/ticket/{id}")
    public String showTicketDetails(@PathVariable int id, Model model) {
        Ticket_To_Buy ticket = ticket_to_buyService.getTicketByID(id);
        model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        model.addAttribute("user_name", httpSession.getAttribute("user_name"));
        model.addAttribute("user_surname", httpSession.getAttribute("user_surname"));
        model.addAttribute("user_login", httpSession.getAttribute("user_login"));
        model.addAttribute("user_bank_balance", httpSession.getAttribute("user_bank_balance"));

        model.addAttribute("event", eventService.getEventByID(ticket.getEventID()));
        model.addAttribute("ticket", ticket);
        model.addAttribute("seat", ticket_to_buyService.getSeatByID(ticket.getSeatID()));

        if (ticket_to_buyService.getIsBusyFromDB(id) == 1) {
            return "busy_ticket.html";
        } else {
            return "ticket_to_buy.html";
        }
    }

    @PostMapping("/ticket/{id}")
    public String handleFormSubmit(@PathVariable int id,
                                   @RequestParam(required = false) String buyTicket, RedirectAttributes redirAttrs) {

        Ticket_To_Buy ticket = ticket_to_buyService.getTicketByID(id);

        if (buyTicket != null) {
            if (ticket.getPrice() > (int) httpSession.getAttribute("user_bank_balance")) {
                redirAttrs.addFlashAttribute("error", "Nie posiadasz wystarczająco dużo środków! Doładuj konto.");
                return "redirect:/ticket/" + id;
            } else {
                if (ticket_to_buyService.buyTicket(ticket) == true) {
                    redirAttrs.addFlashAttribute("success", "Bilet został zakupiony.");
                } else {
                    redirAttrs.addFlashAttribute("error", "Ktoś właśnie kupił ten bilet! Musisz kupić inny.");
                }
            }
        }

        return "redirect:/";
    }

}
