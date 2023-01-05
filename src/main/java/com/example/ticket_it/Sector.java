package com.example.ticket_it;

public class Sector {
    private final int sectorNumber;
    private int home;
    private int away;
    private int vip;
    private final int eventID;

    public Sector(int sectorNumber, int eventID) {
        this.sectorNumber = sectorNumber;
        this.eventID = eventID;
    }

    public Sector(int sectorNumber, int home, int away, int vip, int eventID) {
        this.sectorNumber = sectorNumber;
        this.home = home;
        this.away = away;
        this.vip = vip;
        this.eventID = eventID;
    }
}
