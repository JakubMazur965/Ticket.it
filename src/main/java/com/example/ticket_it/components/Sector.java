package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

@Component
public class Sector {
    private int sectorNumber;
    private int home;
    private int away;
    private int vip;
    private int eventID;

    public int getSectorNumber() {
        return sectorNumber;
    }

    public void setSectorNumber(int sectorNumber) {
        this.sectorNumber = sectorNumber;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }
}
