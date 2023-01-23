package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

@Component
public class Ticket_To_Buy {
    private int sectorNumber;
    private int eventID;
    private int price;
    private int isBusy;
    private int seatID;
    private int ticketToButID;

    public int getSectorNumber() {
        return sectorNumber;
    }

    public void setSectorNumber(int sectorNumber) {
        this.sectorNumber = sectorNumber;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(int isBusy) {
        this.isBusy = isBusy;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getTicketToButID() {
        return ticketToButID;
    }

    public void setTicketToButID(int ticketToButID) {
        this.ticketToButID = ticketToButID;
    }
}
