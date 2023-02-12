package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Seat;
import com.example.ticket_it.components.Ticket_To_Buy;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class Ticket_To_BuyService  {

    @Autowired
    DBHelper dbHelper;

    @Autowired
    HttpSession httpSession;

    public Ticket_To_Buy getTicketByID (int id) {
        Ticket_To_Buy ticket_to_buy;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        ticket_to_buy = dbHelper.getTicketToBuyById(id, connection);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return ticket_to_buy;
    }

    public Seat getSeatByID (int id) {
        Seat seat;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        seat = dbHelper.getSeatById(id, connection);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return seat;
    }

    public void buyTicket (Ticket_To_Buy ticket) {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        dbHelper.buyTicket(connection, ticket, httpSession);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
    }

    public int getIsBusyFromDB(int id) {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        int isBusy = dbHelper.getIsBusy(connection, id);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
        return isBusy;
    }
}
