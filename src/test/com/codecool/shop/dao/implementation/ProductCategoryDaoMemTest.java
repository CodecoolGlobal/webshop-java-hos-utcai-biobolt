package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {
    private ProductCategoryDao productDataStore;
    private ProductCategory test1 = new ProductCategory(1, "test1", "department1","description1");
    private ProductCategory test2 = new ProductCategory(2, "test2", "department2","description2");
    private int lastIndex;


    @BeforeEach
    void init() throws SQLException {
        productDataStore = ProductCategoryDaoMem.getInstance();
        productDataStore.add(test1);
        productDataStore.add(test2);
        List<ProductCategory> data;
        data = productDataStore.getAll();
        lastIndex = data.size() - 1;
    }

    @AfterEach
    void after() {
        productDataStore.remove(1);
        productDataStore.remove(2);
    }



    @Test
    void add() throws SQLException {
        String result = "test2";
        assertEquals(result, productDataStore.getAll().get(lastIndex).getName());
    }

    @Test
    void find() {
        String result = "test1";
        assertEquals(result, productDataStore.find(1).getName());
    }

    @Test
    void testIsElementRemoved() {
            productDataStore.remove(1);
            assertNull(productDataStore.find(1));
    }

    @Test
    void getAll() throws SQLException {
            int result  = productDataStore.getAll().size();
            assertEquals(result, productDataStore.getAll().size());
    }
}