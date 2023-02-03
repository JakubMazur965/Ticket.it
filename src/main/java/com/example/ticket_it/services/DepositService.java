package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class DepositService {

    @Autowired
    HttpSession httpSession;

    public void addMoney (String amount) {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        int amount_ = Integer.parseInt(amount);
        amount_ += (int) (httpSession.getAttribute("user_bank_balance"));
        DBHelper.changeAccountMoney(httpSession, connection, amount_);
        httpSession.setAttribute("user_bank_balance", amount_);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
    }
}
