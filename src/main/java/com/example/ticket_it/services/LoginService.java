package com.example.ticket_it.services;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.User;
import com.example.ticket_it.components.UserSession;
import com.example.ticket_it.components.Utils;
import com.jcraft.jsch.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class LoginService {

    @Autowired
    UserSession userSession;

    public void loginUser(HttpSession session, String username, String password) {
        boolean logger;

        Session sessionSSH = Utils.DBSession();
        Connection connection = Utils.DBConnection(sessionSSH);
        logger = DBHelper.loginUser(connection, username, password);

        if (logger) {
            User user = DBHelper.getUser(username, connection);
            userSession.setUser(user);
            session.setAttribute("user_id", user.getUserID());
            session.setAttribute("user_name", user.getName());
            session.setAttribute("user_surname", user.getSurname());
            session.setAttribute("user_login", user.getLogin());
            session.setAttribute("user_password", user.getPassword());
            session.setAttribute("user_bank_balance", user.getBankBalance());
        }

        Utils.endDBConnection(connection);
        Utils.endDBSession(sessionSSH);
    }
}
