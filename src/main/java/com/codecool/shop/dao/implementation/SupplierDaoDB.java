package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DBDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.DBConnection;
import java.sql.*;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {
    DBDao db = DBConnection.getInstance();
    Supplier supplier;

    @Override
    public void add(Supplier supplier) {

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

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
