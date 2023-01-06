package com.example.ticket_it.repository;

import org.springframework.stereotype.Repository;

@Repository
public class Ticket {
    private int ticketID;
    private int userID;
    private int seatNumber;
    private int rowNumber;
    private int sectorNumber;
    private int eventID;
    private int price;

    public int getTicketID() {
        return ticketID;
    }

    public int getUserID() {
        return userID;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSectorNumber() {
        return sectorNumber;
    }

    public int getEventID() {
        return eventID;
    }

    public int getPrice() {
        return price;
    }
}
