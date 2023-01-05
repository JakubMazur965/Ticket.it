package com.example.ticket_it;

import java.time.LocalTime;
import java.util.Date;

public class Event {
    private final int eventID;
    private final String name;
    private Date eventDate;
    private LocalTime eventStart;
    private LocalTime eventEnd;
    private final String organizer;

    public Event(int eventID, String name, Date eventDate, LocalTime eventStart, LocalTime eventEnd, String organizer) {
        this.eventID = eventID;
        this.name = name;
        this.eventDate = eventDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.organizer = organizer;
    }
}
