package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoMemTest {
    private ProductDao productDao;
    private List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void init() {
        productDao = ProductDaoMem.getInstance();

        Product p1 = new Product("YEEET", 1,"USD", "nem", new ProductCategory("kek", "anyad", ":)"), new Supplier("jozsi", "aki am bela"));
        Product p2 = new Product("asd", 2,"USD", "nem", new ProductCategory("dolog", "anyad", ":("), new Supplier("bela", "amugy jozsi"));
        productList.add(p1);
        productList.add(p2);
        productDao.add(p1);
        productDao.add(p2);
    }

    @AfterEach
    public void finish() {
        productDao.remove(0);
        productDao.remove(1);
    }

    @Test
    public void testIsProductAdded() {
        ProductCategory c = new ProductCategory("kek", "anyad", ":)");
        Supplier s = new Supplier("jozsi", "aki am bela");
        Product p = new Product(100,"friss száraz kenyér", 3,"USD", "nem", c, s);
        productDao.add(p);
        assertEquals(productDao.find(100), p);
        productDao.remove(100);
    }

    @Test
    public void testIsFindMatching() {
        ProductCategory c = new ProductCategory("kek", "anyad", ":)");
        Supplier s = new Supplier("jozsi", "aki am bela");
        Product p = new Product(100,"friss száraz kenyér", 3,"USD", "nem", c, s);
        productDao.add(p);
        assertEquals(productDao.find(100), p);
        productDao.remove(100);
    }

    @Test
    public void testRemove() {
        ProductCategory c = new ProductCategory("kek", "anyad", ":)");
        Supplier s = new Supplier("jozsi", "aki am bela");
        Product p = new Product(100,"friss száraz kenyér", 3,"USD", "nem", c, s);

        productDao.add(p);
        int ps = productDao.getAll().size();
        productDao.remove(100);
        assertEquals(ps-1, productDao.getAll().size());
    }

    @Test
    public void testGetAllRuns() {
        assertNotNull(productDao.getAll());
    }

    @Test
    public void testGetBy() {
        ProductCategory c = new ProductCategory("kek", "anyad", ":)");
        Supplier s = new Supplier("jozsi", "aki am bela");
        Product p = new Product(100,"friss száraz kenyér", 3,"USD", "nem", c, s);
        productDao.add(p);
        productDao.getBy(s);
        productDao.getBy(c);
        productDao.remove(100);
    }

}
