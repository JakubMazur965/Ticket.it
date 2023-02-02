package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Ticket_To_Buy;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class Ticket_To_BuyService  {
    public static Ticket_To_Buy getTicketByID (int id) {
        Ticket_To_Buy ticket_to_buy = null;

        /*
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        ticket_to_buy = DBHelper.(connection, id);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

         */

        return ticket_to_buy;

    }
}
