package com.codecool.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ani on 2016.11.13..
 */
public class DBConnection {


    protected static final String DATABASE = "jdbc:postgresql://173.212.197.253:54331/tejfi";
    protected static final String DB_USER = "postgres";
    protected static final String DB_PASSWORD = "37bca414725fa71189323a0c24018c35";


    private static DBConnection instance = null;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }




    public Connection getConnection() throws SQLException {
        System.out.println(DB_PASSWORD);
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}