package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.User;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class RegisterService {

    public void addUser(String name, String surname, String username, String password) {

        password = encode(password);
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(username);
        user.setPassword(password);
        user.setBankBalance(0);

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        DBHelper.addUser(connection, user);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
    }

    public String encode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}

