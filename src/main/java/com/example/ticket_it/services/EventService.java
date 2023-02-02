package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Event;
import com.example.ticket_it.components.Ticket_To_Buy;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;


@Service
public class EventService {
    public static Event findById(int id) {
        Event event;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        event = DBHelper.getEventByID(connection, id);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return event;

    }

    public static List<Ticket_To_Buy> getAllTickets() {
        List <Ticket_To_Buy> tickets = null;

        /*Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        tickets = DBHelper.(connection);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);*/

        return tickets;
    }
}
