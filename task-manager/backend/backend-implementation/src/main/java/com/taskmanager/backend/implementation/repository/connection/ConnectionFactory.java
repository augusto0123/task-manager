package com.taskmanager.backend.implementation.repository.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection = null;
    private static final String username = "postgres";
    private static final String password = "postgres";
    private static final String url = "jdbc:postgresql://localhost:5432/taskmanager";

    public static Connection getConnection(){
        try {
            if (connection == null){
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
