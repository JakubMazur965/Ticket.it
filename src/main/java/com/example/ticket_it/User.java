package com.example.ticket_it;

public class User {
    private final int userID;
    private final String name;
    private final String surname;
    private String email;
    private final int personalID;
    private int bankBalance;

    public User(int userID, String name, String surname, String email, int personalID, int bankBalance) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalID = personalID;
        this.bankBalance = bankBalance;
    }
}
