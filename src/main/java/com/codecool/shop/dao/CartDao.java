package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void addOneProduct(String string);
    void removeOneProduct(String string);
    void emptyCart();
    void addProductToShoppingCart(int i);
    int getSize();
    HashMap<Product, Integer> getAll();
}

