package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DBConnection;
import com.codecool.shop.config.DBDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    DBDao db = DBConnection.getInstance();
    Product product;

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (name, defaultprice, currencystring, description, categoryid, supplierid) " +
                "VALUES ('"+ product.getName() +"', '"+
                product.getDefaultPrice() +"', '" +
                product.getDefaultCurrency() + "', '"+
                product.getDescription() + "', '" +
                product.getProductCategory() + "', '"+
                product.getSupplier() + "' );";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
