package com.example.ticket_it;

public class Ticket_To_Buy {
    private final int seatNumber;
    private final int rowNumber;
    private final int sectorNumber;
    private final int eventID;
    private int price;
    private int isBusy;

    public Ticket_To_Buy(int seatNumber, int rowNumber, int sectorNumber, int eventID, int price, int isBusy) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.sectorNumber = sectorNumber;
        this.eventID = eventID;
        this.price = price;
        this.isBusy = isBusy;
    }
}
