package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Date;

@Component
public class Event {
    private int eventID;
    private String name;
    private Date eventDate;
    private Time eventStart;
    private Time eventEnd;
    private String organizer;
    private int eventClass;

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventStart() {
        return eventStart;
    }

    public void setEventStart(Time eventStart) {
        this.eventStart = eventStart;
    }

    public Time getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Time eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getEventClass() {
        return eventClass;
    }

    public void setEventClass(int eventClass) {
        this.eventClass = eventClass;
    }
}
