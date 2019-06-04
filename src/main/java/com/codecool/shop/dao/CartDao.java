package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public interface CartDao {

    void addOneProduct(String string);
    void removeOneProduct(String string);
    void emptyCart();
    void addProductToShoppingCart(Integer id);
    int getCarDataSize();
    float getTotalPrice();
    HashMap<Product, Integer> getAll();
}

