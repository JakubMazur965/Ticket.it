package com.example.ticket_it;

public class Seat {
    private final int seatNumber;
    private final int rowNumber;
    private final int sectorNumber;

    public Seat(int seatNumber, int rowNumber, int sectorNumber) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.sectorNumber = sectorNumber;
    }
}
