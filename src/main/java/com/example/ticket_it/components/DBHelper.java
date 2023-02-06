package com.example.ticket_it.components;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBHelper {

    public static void addEvent(Event event, Connection connection) {

        String query = "INSERT INTO event(event_id, name, event_date, event_start, event_end, organizer, event_class) VALUES(?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to event_table
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

        String query = "INSERT INTO sector(sector_number, home, away, vip) VALUES(?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to event_table
            preparedStatement.setInt(1, sector.getSectorNumber());
            preparedStatement.setInt(2, sector.getHome());
            preparedStatement.setInt(3, sector.getAway());
            preparedStatement.setInt(4, sector.getVip());

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

            while (rs.next()) {
                event.setEventID(rs.getInt(1));
                event.setName(rs.getString(2));
                event.setEventDate(rs.getDate(3));
                event.setEventStart(rs.getTime(4));
                event.setEventEnd(rs.getTime(5));
                event.setOrganizer(rs.getString(6));
                event.setEventClass(rs.getInt(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return event;
    }

    public static void addTicketToBuy(Connection connection, Ticket_To_Buy ticket_to_buy) {

        String query = "INSERT INTO ticket_to_buy(sector_number, event_id, price, is_busy, seat_id, ticket_to_buy_id) VALUES(?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to ticket_to_buy table
            preparedStatement.setInt(1, ticket_to_buy.getSectorNumber());
            preparedStatement.setInt(2, ticket_to_buy.getEventID());
            preparedStatement.setInt(3, ticket_to_buy.getPrice());
            preparedStatement.setInt(4, ticket_to_buy.getIsBusy());
            preparedStatement.setInt(5, ticket_to_buy.getSeatID());
            preparedStatement.setInt(6, ticket_to_buy.getTicketToBuyID());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Sector getSectorBySectorNumber(Connection connection, int id) {
        Sector sector = new Sector();

        String query = "SELECT * FROM sector WHERE sector_number = " + id + ";";

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
        String query = "INSERT INTO user_table(name, surname, login, password, bank_balance) VALUES(?,?,?,?,?);";

        if (!user.getName().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid name.");
        }
        if (!user.getSurname().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid surname.");
        }
        if (!user.getLogin().matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Invalid username.");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 0);

            preparedStatement.executeUpdate();

            System.out.println("The row has been added.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean loginUser(Connection connection, String username, String password) {
        String query = "SELECT * FROM user_table WHERE user_table.login = ? ;";

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Invalid username.");
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("password");
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(password, passwordHash)) {
                    return true;
                } else {
                    System.out.println("hasło jest nieprawidłowe");
                }
            } else {
                System.out.println("nie znaleziono użytkownika o podanych danych");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static User getUser(String login, Connection connection) {
        User user = new User();

        String query = "SELECT * FROM user_table WHERE user_table.login = ? ;";

        if (!login.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Invalid username.");
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            rs.next();

            user.setUserID(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setLogin(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setBankBalance(rs.getInt(6));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<Ticket_To_Buy> getTicketToBuyByEventIdAndSectorNumber (Connection connection, int eventId, int sectorNumber) {
        List<Ticket_To_Buy> tickets = new ArrayList<>();
        String query = "SELECT * FROM ticket_to_buy WHERE ticket_to_buy.event_id = ? AND ticket_to_buy.sector_number = ? AND is_busy = 0;";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, eventId);
            statement.setInt(2, sectorNumber);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ticket_To_Buy ticket = new Ticket_To_Buy();
                ticket.setSectorNumber(rs.getInt(1));
                ticket.setEventID(rs.getInt(2));
                ticket.setPrice(rs.getInt(3));
                ticket.setIsBusy(rs.getInt(4));
                ticket.setSeatID(rs.getInt(5));
                ticket.setTicketToBuyID(rs.getInt(6));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public static void changeAccountMoney(HttpSession httpSession, Connection connection, int amount) {
        String query = "UPDATE user_table SET bank_balance = " + amount + " WHERE user_table.login = ? ;";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, (String) httpSession.getAttribute("user_login"));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Seat> getSeatsBySectorNumber (int sectorNumber, Connection connection) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seat WHERE seat.sector_number = ? ;";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sectorNumber);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Seat seat = new Seat();
                seat.setSeatNumber(rs.getInt(1));
                seat.setRowNumber(rs.getInt(2));
                seat.setSectorNumber(rs.getInt(3));
                seat.setSeatID(rs.getInt(4));
                seats.add(seat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seats;
    }

    public Ticket_To_Buy getTicketToBuyById (int id, Connection connection) {
        Ticket_To_Buy ticket = new Ticket_To_Buy();
        String query = "SELECT * FROM ticket_to_buy WHERE ticket_to_buy_id = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ticket.setSectorNumber(rs.getInt(1));
                ticket.setEventID(rs.getInt(2));
                ticket.setPrice(rs.getInt(3));
                ticket.setIsBusy(rs.getInt(4));
                ticket.setSeatID(rs.getInt(5));
                ticket.setTicketToBuyID(rs.getInt(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }

    public Seat getSeatById (int id, Connection connection) {
        Seat seat = new Seat();
        String query = "SELECT * FROM seat WHERE seat.seat_id = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                seat.setSeatNumber(rs.getInt(1));
                seat.setRowNumber(rs.getInt(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seat;
    }

    public String homeAwayVIP (Connection connection, int eventId, int sectorNumber) {
        String hav = "";
        String query = "SELECT * FROM sector, event WHERE event.event_id = ? AND sector.sector_number = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, eventId);
            statement.setInt(2, sectorNumber);
            ResultSet rs = statement.executeQuery();

            rs.next();

            if (rs.getInt(2) == 1) {hav = "h";}
            else if (rs.getInt(3) == 1) {hav = "a";}
            else if (rs.getInt(4) == 1) {hav = "v";}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hav;
    }

    public void buyTicket (Connection connection, Ticket_To_Buy ticket, HttpSession httpSession) {
        String query1 = "UPDATE ticket_to_buy SET is_busy = 1 WHERE ticket_to_buy_id = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setInt(1, ticket.getTicketToBuyID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query2 = "INSERT INTO ticket(user_id, sector_number, event_id, price, seat_id) VALUES(?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query2)) {
            preparedStatement.setInt(1, (int) httpSession.getAttribute("user_id"));
            preparedStatement.setInt(2, ticket.getSectorNumber());
            preparedStatement.setInt(3, ticket.getEventID());
            preparedStatement.setInt(4, ticket.getPrice());
            preparedStatement.setInt(5, ticket.getSeatID());

            preparedStatement.executeUpdate();

            System.out.println("The row has been added.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String query3 = "UPDATE user_table SET bank_balance = ? WHERE user_id = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query3);
            int bankBalance = (int) httpSession.getAttribute("user_bank_balance") - ticket.getPrice();
            httpSession.setAttribute("user_bank_balance", bankBalance);
            statement.setInt(1, bankBalance);
            statement.setInt(2, (int) httpSession.getAttribute("user_id"));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Seat> getTicketHistorySeats(Connection connection, HttpSession httpSession) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seat WHERE seat_id = any(SELECT seat_id FROM ticket WHERE user_id = ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (int) httpSession.getAttribute("user_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setSeatNumber(rs.getInt(1));
                seat.setRowNumber(rs.getInt(2));
                seat.setSectorNumber(rs.getInt(3));
                seat.setSeatID(rs.getInt(4));
                seats.add(seat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seats;
    }

    public List<Event> getTicketHistoryEvents(Connection connection, HttpSession httpSession) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event WHERE event_id = any(SELECT event_id FROM ticket WHERE user_id = ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (int) httpSession.getAttribute("user_id"));
            ResultSet rs = statement.executeQuery();
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

    public List<Ticket> getTicketHistory(Connection connection, HttpSession httpSession) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM ticket WHERE user_id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (int) httpSession.getAttribute("user_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketID(rs.getInt(1));
                ticket.setUserID(rs.getInt(2));
                ticket.setSectorNumber(rs.getInt(3));
                ticket.setEventID(rs.getInt(4));
                ticket.setPrice(rs.getInt(5));
                ticket.setSeatID(rs.getInt(6));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }
}
