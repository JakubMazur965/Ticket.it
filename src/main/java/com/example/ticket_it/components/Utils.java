package com.example.ticket_it.components;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Utils {

    private static SSHTunnel sshTunnel;

    @Autowired
    public Utils(SSHTunnel sshTunnel) {
        this.sshTunnel = sshTunnel;
    }

    // Adding ticket_to_buy depend on event_id
    public static void addTicketsToBuy() {
        // Start SSH Tunnel session
        Session session = null;
        try {
            session = sshTunnel.startTunnel();
        } catch (JSchException e) {
            System.out.println(e.getMessage());
        }

        // Start connection to the DataBase
        Connection connection =  sshTunnel.connectionToDataBase();

        // choose event
        Event event = DBHelper.getEventByID(connection, 3);

        Ticket_To_Buy ticket_to_buy = new Ticket_To_Buy();
        ticket_to_buy.setEventID(event.getEventID());
        ticket_to_buy.setIsBusy(0);

        for (int i = 1; i < 201; i++) {

            Seat seat = DBHelper.getSeatByID(connection, i);
            ticket_to_buy.setSectorNumber(seat.getSectorNumber());
            ticket_to_buy.setSeatID(seat.getSeatID());

            Sector sector = DBHelper.getSectorBySectorNumber(connection, seat.getSectorNumber());
            int price = (event.getEventClass() * 100) * ((sector.getHome() * 1) + (sector.getAway() * 2) + (sector.getVip() * 5));
            ticket_to_buy.setPrice(price);

            DBHelper.addTicketToBuy(connection, ticket_to_buy);
        }

        System.out.println("Tickets have been added");

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
