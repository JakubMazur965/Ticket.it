package com.example.ticket_it.controllers;

import com.example.ticket_it.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DepositController {

    @Autowired
    DepositService depositService;

    @PostMapping("/deposit")
    public String cashPayment(@RequestParam("amount") String amount) {
        depositService.addMoney(amount);
        return "redirect:/";
    }
}
