package com.example.ticket_it.repository;

import org.springframework.stereotype.Repository;

@Repository
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
