package com.example.ticket_it.services;

import com.example.ticket_it.components.*;
import com.jcraft.jsch.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class TicketHistoryService {

    @Autowired
    HttpSession httpSession;

    @Autowired
    DBHelper dbHelper;

    public List<Seat> getSeatsHistory() {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        List<Seat> seats = dbHelper.getTicketHistorySeats(connection, httpSession);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
        return seats;
    }

    public List<Event> getEventsHistory() {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        List<Event> events = dbHelper.getTicketHistoryEvents(connection, httpSession);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
        return events;
    }

    public List<Ticket> getTicketsHistory() {
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        List<Ticket> tickets = dbHelper.getTicketHistory(connection, httpSession);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
        return tickets;
    }

}
