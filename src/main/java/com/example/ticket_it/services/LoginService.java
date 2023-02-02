package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.User;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class LoginService {

    @Autowired
    RegisterService registerService;

    public void loginUser(String username, String password) {

        password = registerService.encode(password);

        Session session = Utils.DBSession();
        Connection connection = Utils.DBConnection(session);
        DBHelper.loginUser(connection, username, password);
        Utils.endDBConnection(connection);
        Utils.endDBSession(session);
    }
}
