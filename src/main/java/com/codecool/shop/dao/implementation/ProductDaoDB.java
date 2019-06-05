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
    private static ProductDaoDB INSTANCE;

    SupplierDaoDB supplierDaoDB = SupplierDaoDB.getInstance();
    DBDao db = DBConnection.getInstance();
    ProductCategoryDB productCategoryDB = ProductCategoryDB.getInstance();
    Product product;
    List<Product> productList = new ArrayList<>();

    private  ProductDaoDB(){}

    public static ProductDaoDB getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ProductDaoDB();
        }

        return INSTANCE;
    }

    @Override
    public void add(Product product) {

        int productCategoryId = productCategoryDB.find(product.getProductCategory().getId()).getId();
        int supplierId = supplierDaoDB.find(product.getSupplier().getId()).getId();


        String query = "INSERT INTO products (name, defaultprice, currencystring, description, categoryid, supplierid) " +
                "VALUES ('" + product.getName() + "', '" +
                product.getDefaultPrice() + "', '" +
                product.getDefaultCurrency() + "', '" +
                product.getDescription() + "', '" +
                productCategoryId + "', '" +
                supplierId + "' );";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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
                product.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = '" + id + "';";
        DBConnection.getInstance().executeQuery(query);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        productList.clear();
        String query = "SELECT * FROM products";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = supplierDaoDB.find(resultSet.getInt("supplierid"));
                ProductCategory productCategory = productCategoryDB.find(resultSet.getInt("categoryid"));
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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

    @Override
    public List<Product> getBy(Supplier supplier) {
        productList.clear();
        String query = "SELECT * FROM products WHERE supplierid ='" + supplier.getId() + "';";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                ProductCategory productCategory = productCategoryDB.find(resultSet.getInt("categoryid"));
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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

