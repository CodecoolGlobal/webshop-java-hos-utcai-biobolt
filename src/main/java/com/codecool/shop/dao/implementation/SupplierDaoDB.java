package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DBDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {
    DBDao db = DBConnection.getInstance();
    Supplier supplier;

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (name, description) VALUES ('"+ supplier.getName() +"', '"+ supplier.getDescription() +"');";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                supplier = new Supplier(resultSet.getString("name"), resultSet.getString("description"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM supplier WHERE id = '"+ id +"';";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                resultList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
