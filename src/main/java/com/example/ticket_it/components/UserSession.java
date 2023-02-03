package com.example.ticket_it.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    @Autowired
    private User user;

    @PreAuthorize("isAuthenticated()")
    public User getUser() {
        return user;
    }

    @PreAuthorize("isAuthenticated()")
    public void setUser(User user) {
        this.user = user;
    }
}