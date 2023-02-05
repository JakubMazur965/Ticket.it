package com.example.ticket_it.controllers;

import com.example.ticket_it.services.DepositService;
import com.example.ticket_it.services.Ticket_To_BuyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;


@Controller
public class DepositController {

    @Autowired
    DepositService depositService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    Ticket_To_BuyService ticket_to_buyService;

    @PostMapping("/deposit")
    public String payment(@RequestParam("amount") String amount) {
        depositService.addMoney(amount);
        return "redirect:/";
    }

    @RequestMapping("/deposit/{id}")
    public String addMoneyToBuyTicket (@PathVariable int id, Model model) {
        model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        model.addAttribute("user_name", httpSession.getAttribute("user_name"));
        model.addAttribute("user_surname", httpSession.getAttribute("user_surname"));
        model.addAttribute("user_login", httpSession.getAttribute("user_login"));
        model.addAttribute("user_bank_balance", httpSession.getAttribute("user_bank_balance"));
        model.addAttribute("isLoggedIn", httpSession.getAttribute("isLoggedIn"));
        model.addAttribute("ticket", ticket_to_buyService.getTicketByID(id));
        return "deposit_ticket.html";
    }

    @PostMapping("/deposit/{id}")
    public String buyTicket(@PathVariable int id, @RequestParam("amount") String amount) {
        depositService.addMoney(amount);
        return "redirect:/ticket/"+id;
    }
}
