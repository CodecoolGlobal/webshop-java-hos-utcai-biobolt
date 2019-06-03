package com.codecool.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ani on 2016.11.13..
 */
public class DBConnection {

    private static final String DATABASE = System.getenv("DATABASE");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");


    public DBConnection() {
    }


    private Connection getConnection() throws SQLException {
        System.out.println(DB_PASSWORD);
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}