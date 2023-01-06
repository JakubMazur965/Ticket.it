package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

@Component
public class Seat {
    private int seatNumber;
    private int rowNumber;
    private int sectorNumber;

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSectorNumber() {
        return sectorNumber;
    }
}
