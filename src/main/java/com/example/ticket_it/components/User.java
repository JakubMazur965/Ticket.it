package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int userID;
    private String name;
    private String surname;
    private String email;
    private int personalID;
    private int bankBalance;

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getPersonalID() {
        return personalID;
    }

    public int getBankBalance() {
        return bankBalance;
    }
}
