package com.example.ticket_it.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBHelper {

    public static void addEvent(Event event, Connection connection) {

        String query = "INSERT INTO event(event_id, name, event_date, event_start, event_end, organizer, event_class) VALUES(?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to event table
            preparedStatement.setInt(1, event.getEventID());
            preparedStatement.setString(2, event.getName());
            preparedStatement.setDate(3, event.getEventDate());
            preparedStatement.setTime(4, event.getEventStart());
            preparedStatement.setTime(5, event.getEventEnd());
            preparedStatement.setString(6, event.getOrganizer());
            preparedStatement.setInt(7, event.getEventClass());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSector(Sector sector, Connection connection) {

        String query = "INSERT INTO sector(sector_number, home, away, vip, event_id) VALUES(?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to event table
            preparedStatement.setInt(1, sector.getSectorNumber());
            preparedStatement.setInt(2, sector.getHome());
            preparedStatement.setInt(3, sector.getAway());
            preparedStatement.setInt(4, sector.getVip());
            preparedStatement.setInt(5, sector.getEventID());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSeat(Seat seat, Connection connection) {

        String query = "INSERT INTO seat(seat_number, row_number, sector_number, seat_id) VALUES(?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to seat table
            preparedStatement.setInt(1, seat.getSeatNumber());
            preparedStatement.setInt(2, seat.getRowNumber());
            preparedStatement.setInt(3, seat.getSectorNumber());
            preparedStatement.setInt(4, seat.getSeatID());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Seat getSeatByID(Connection connection, int id) {
        Seat seat = new Seat();

        String query = "SELECT * FROM seat WHERE seat_id = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.next();

            seat.setSeatNumber(rs.getInt(1));
            seat.setRowNumber(rs.getInt(2));
            seat.setSectorNumber(rs.getInt(3));
            seat.setSeatID(rs.getInt(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return seat;
    }

    public static Event getEventByID(Connection connection, int id) {
        Event event = new Event();

        String query = "SELECT * FROM event WHERE event_id = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.next();

            event.setEventID(rs.getInt(1));
            event.setName(rs.getString(2));
            event.setEventDate(rs.getDate(3));
            event.setEventStart(rs.getTime(4));
            event.setEventEnd(rs.getTime(5));
            event.setOrganizer(rs.getString(6));
            event.setEventClass(rs.getInt(7));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return event;
    }

    public static void addTicketToBuy(Connection connection, Ticket_To_Buy ticket_to_buy) {

        String query = "INSERT INTO ticket_to_buy(sector_number, event_id, price, is_busy, seat_id, ticket_to_but_id) VALUES(?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to ticket_to_buy table
            preparedStatement.setInt(1, ticket_to_buy.getSectorNumber());
            preparedStatement.setInt(2, ticket_to_buy.getEventID());
            preparedStatement.setInt(3, ticket_to_buy.getPrice());
            preparedStatement.setInt(4, ticket_to_buy.getIsBusy());
            preparedStatement.setInt(5, ticket_to_buy.getSeatID());
            preparedStatement.setInt(6, ticket_to_buy.getTicketToButID());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Sector getSectorBySectorNumber(Connection connection, int id) {
        Sector sector = new Sector();

        String query = "SELECT * FROM sector WHERE sector_number = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.next();

            sector.setSectorNumber(rs.getInt(1));
            sector.setHome(rs.getInt(2));
            sector.setAway(rs.getInt(3));
            sector.setVip(rs.getInt(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sector;
    }

    public static List<Event> getEvents(Connection connection) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event ORDER BY event_date;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setName(rs.getString(2));
                event.setEventDate(rs.getDate(3));
                event.setEventStart(rs.getTime(4));
                event.setEventEnd(rs.getTime(5));
                event.setOrganizer(rs.getString(6));
                event.setEventClass(rs.getInt(7));

                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return events;
    }

    public static void addUser(Connection connection, User user) {
        String query = "INSERT INTO user(user_id, name, surname, login, password, bank_balance) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to ticket_to_buy table
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, 0);

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
