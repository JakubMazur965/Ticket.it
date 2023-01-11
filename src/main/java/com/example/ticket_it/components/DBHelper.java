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

    public static void checkConnection(Connection connection) {
        String query = "SELECT * FROM event WHERE event_id = 2;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            System.out.println(resultSet.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
