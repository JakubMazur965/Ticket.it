package com.example.ticket_it;

import com.example.ticket_it.components.DBHelper;
import com.example.ticket_it.components.Event;
import com.example.ticket_it.components.SSHTunnel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    private static SSHTunnel sshTunnel;

    @Autowired
    public Application(SSHTunnel sshTunnel) {
        this.sshTunnel = sshTunnel;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // Start SSH Tunnel session
        Session session = null;
        try {
            session = sshTunnel.startTunnel();
        } catch (JSchException e) {
            System.out.println(e.getMessage());
        }

        // Start connection to the DataBase
        Connection connection =  sshTunnel.connectionToDataBase();

        // Creating a new Event

        /*
        Event event = new Event();
        event.setEventID(2);
        event.setName("Koncert");
        Date date = new Date(2023, 1, 7);
        event.setEventDate(date);
        Time timeStart = new Time(12, 0, 0);
        event.setEventStart(timeStart);
        Time timeEnd = new Time(16, 30, 0);
        event.setEventEnd(timeEnd);
        event.setOrganizer("Sabaton");

        DBHelper.addEvent(event, connection);
        */


        // end connection
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // end SSH Tunnel session
        // assert - check that session is not null,
        // if session is null, assert will throw an "Assertion Error"
        assert session != null;
        session.disconnect();

    }

}
