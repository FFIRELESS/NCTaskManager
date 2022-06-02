package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.*;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.TaskListFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class DatabaseController extends BaseController {
    public static final String urlDB = "jdbc:postgresql://127.0.0.1:5432/postgres";
    public static final String usernameDB = "postgres";
    public static final String passwordDB = "di5223202ma";
    private Connection dbConnection;

    public void connect() {
        System.out.println("Testing connection to PostgreSQL JDBC")
        ;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            dbConnection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (dbConnection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public void disconnect() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection to database");
        }
    }

    public AbstractTaskList readTasks() {
        AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");

            while (resultSet.next()) {
                Task task;
                if (resultSet.getString("end_date") != null) {
                    task = new Task(resultSet.getString("title"),
                            UUID.fromString(resultSet.getString("user_id")),
                            LocalDateTime.parse(resultSet.getString("start_date")),
                            LocalDateTime.parse(resultSet.getString("end_date")),
                            resultSet.getInt("interval"));
                } else {
                    task = new Task(resultSet.getString("title"),
                            UUID.fromString(resultSet.getString("user_id")),
                            LocalDateTime.parse(resultSet.getString("start_date")));
                }
                tasks.add(task);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public ArrayUserList readUsers() {
        ArrayUserList users = new ArrayUserList();

        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user;
                user = new User(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
