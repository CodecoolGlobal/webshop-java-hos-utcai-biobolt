package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DBConnection;
import com.codecool.shop.config.DBDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    DBDao db = DBConnection.getInstance();
    ProductCategoryDB productCategoryDB;
    SupplierDaoDB supplierDaoDB;
    Product product;
    List<Product> productList = new ArrayList<>();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (name, defaultprice, currencystring, description, categoryid, supplierid) " +
                "VALUES ('" + product.getName() + "', '" +
                product.getDefaultPrice() + "', '" +
                product.getDefaultCurrency() + "', '" +
                product.getDescription() + "', '" +
                product.getProductCategory() + "', '" +
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

        String query = "SELECT * FROM products WHERE id='" + id + "';";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ) {
            while (resultSet.next()) {
                ProductCategory productCategory = productCategoryDB.find(resultSet.getInt("categoryid"));
                Supplier supplier = supplierDaoDB.find(resultSet.getInt(resultSet.getInt("supplierid")));
                product = new Product(resultSet.getString("name"),
                        Float.parseFloat(resultSet.getString("defaultprice")),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
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
        productList.clear();
        String query = "SELECT * FROM products WHERE categoryid='" + productCategory.getId() + "';";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ) {
            while (resultSet.next()) {
                Supplier supplier = supplierDaoDB.find(resultSet.getInt(resultSet.getInt("supplierid")));
                productList.add(new Product(resultSet.getString("name"),
                        Float.parseFloat(resultSet.getString("defaultprice")),
                        resultSet.getString("currencystring"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}

