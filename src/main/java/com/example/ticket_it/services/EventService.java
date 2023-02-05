package com.example.ticket_it.services;

import com.example.ticket_it.components.*;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventService {

    @Autowired
    DBHelper dbHelper;
    public Event getEventByID (int id) {
        Event event;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        event = DBHelper.getEventByID(connection, id);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return event;

    }

    public String homeAwayVIP(int eventId, int sectorNumber) {
        String hav;
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        hav = dbHelper.homeAwayVIP(connection, eventId, sectorNumber);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return hav;
    }

    public List<Ticket_To_Buy> getTicketsBySector(int eventId, int sectorNumber) {
        List <Ticket_To_Buy> tickets;
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        tickets = dbHelper.getTicketToBuyByEventIdAndSectorNumber(connection, eventId, sectorNumber);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return tickets;
    }

    public List<Seat> getSeatBySector (int sectorNumber) {
        List <Seat> seats;
        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        seats = dbHelper.getSeatsBySectorNumber(sectorNumber, connection);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return seats;
    }
}
