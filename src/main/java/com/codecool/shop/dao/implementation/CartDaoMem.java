package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class CartDaoMem implements CartDao {

    private HashMap<Product, Integer> cartData = new LinkedHashMap<>();
    private static CartDaoMem instance = null;


    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void addOneProduct(String productName) {
        for (Product key : cartData.keySet()) {
            if (productName.equals(key.getName())) {
                cartData.put(key, cartData.get(key) + 1);
            }
        }
    }

    @Override
    public void removeOneProduct(String productName) {
        for (Product key : cartData.keySet()) {
//            if (cartData.get(product) == 1) {
//                cartData.remove(product);
//            }
            if (productName.equals(key.getName())) {
                cartData.put(key, cartData.get(key) - 1);
            }
        }
    }


    @Override
    public void emptyCart() {
        cartData.clear();
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return cartData;
    }
}
