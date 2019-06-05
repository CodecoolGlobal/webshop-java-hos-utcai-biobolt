package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DBConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDB implements ProductCategoryDao {
    ProductCategory productCategory;
    DBConnection dbConnection = DBConnection.getInstance();


    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO productcategory (name,department,description) VALUES('" + category.getName() + "', '" + category.getDepartment() + "', '" + category.getDescription() + "');";
        DBConnection.getInstance().executeQuery(query);
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM productcategory WHERE id='" + id + "';";
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ) {
            while (resultSet.next()) {
                productCategory = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategory;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE id = '" + id + "';";
        DBConnection.getInstance().executeQuery(query);
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> resultList = new ArrayList<>();
        String query = "SELECT * FROM productcategory";

        try (Connection connection = dbConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
                productCategory.setId(resultSet.getInt("id"));
                resultList.add(productCategory);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
