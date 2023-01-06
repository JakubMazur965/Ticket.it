package com.example.ticket_it.repository;

import org.springframework.stereotype.Repository;

@Repository
public class Ticket_To_Buy {
    private int seatNumber;
    private int rowNumber;
    private int sectorNumber;
    private int eventID;
    private int price;
    private int isBusy;

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

    public int getIsBusy() {
        return isBusy;
    }
}
