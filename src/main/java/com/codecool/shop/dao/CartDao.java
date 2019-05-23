package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void addOneProduct(Product product);
    void removeOneProduct(Product product);
    void removeAllProduct(Product product);
    void emptyCart();

    HashMap<Product, Integer> getAll();
}

