package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Event;
import com.example.ticket_it.components.Ticket_To_Buy;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventService {
    public static Event getEventByID (int id) {
        Event event;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        event = DBHelper.getEventByID(connection, id);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return event;

    }

    public static List<Ticket_To_Buy> getTicketsBySector(int eventId, int sectorNumber) {
        List <Ticket_To_Buy> tickets = new ArrayList<>();
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        tickets = DBHelper.getTicketToBuyByEventIdAndSectorNumber(connection, eventId, sectorNumber);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return tickets;
    }
}
