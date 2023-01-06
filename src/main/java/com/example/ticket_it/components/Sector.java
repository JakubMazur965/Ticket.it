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

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    public int getVip() {
        return vip;
    }

    public int getEventID() {
        return eventID;
    }
}
