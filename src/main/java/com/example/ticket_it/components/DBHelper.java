package com.example.ticket_it.components;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DBHelper {

    public static void addEvent(Event event, Connection connection) {

        String query = "INSERT INTO event(event_id, name, event_date, event_start, event_end, organizer) VALUES(?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // adding row to event table
            preparedStatement.setInt(1, event.getEventID());
            preparedStatement.setString(2, event.getName());
            preparedStatement.setDate(3, event.getEventDate());
            preparedStatement.setTime(4, event.getEventStart());
            preparedStatement.setTime(5, event.getEventEnd());
            preparedStatement.setString(6, event.getOrganizer());

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
            // adding row to event table
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
}
