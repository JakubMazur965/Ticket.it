package com.example.ticket_it.repository;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Date;

@Repository
public class Event {
    private int eventID;
    private String name;
    private Date eventDate;
    private Time eventStart;
    private Time eventEnd;
    private String organizer;

    public int getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getEventStart() {
        return eventStart;
    }

    public Time getEventEnd() {
        return eventEnd;
    }

    public String getOrganizer() {
        return organizer;
    }
}
