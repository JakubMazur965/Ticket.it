package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Event;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageService {
    public static List<Event> getEvents() {
        List <Event> events;

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        events = DBHelper.getEvents(connection);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);

        return events;
    }
}
