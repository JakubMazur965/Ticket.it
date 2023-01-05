package com.example.ticket_it;

public class Ticket {
    private final int ticketID;
    private final int userID;
    private final int seatNumber;
    private final int rowNumber;
    private final int sectorNumber;
    private final int eventID;
    private final int price;

    public Ticket(int ticketID, int userID, int seatNumber, int rowNumber, int sectorNumber, int eventID, int price) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.sectorNumber = sectorNumber;
        this.eventID = eventID;
        this.price = price;
    }
}
