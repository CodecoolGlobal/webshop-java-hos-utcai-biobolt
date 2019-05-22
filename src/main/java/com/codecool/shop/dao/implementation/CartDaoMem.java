package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;

public class CartDaoMem implements CartDao {

    private HashMap<Product, Integer> cartData = new HashMap<>();
    private static CartDaoMem instance = null;

    private CartDaoMem(){
    }

    public static CartDaoMem getInstance(){
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void addOneProduct(Product product) {
        if (cartData.containsKey(product)){
            cartData.put(product, +1);
        }

    }

    @Override
    public void removeOneProduct(Product product) {

    }

    @Override
    public void removeAllProduct(Product product) {

    }

    @Override
    public void emptyCart() {

    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return null;
    }
}
