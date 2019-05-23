package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    ProductDao productDataStore = ProductDaoMem.getInstance();

    private HashMap<Product, Integer> cartData = new LinkedHashMap<>();
    private static CartDaoMem instance = null;
   // public static long count = 0;


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


//    for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
//        String value = iterator.next();
//        if (value.length() > 5) {
//            iterator.remove();
//        }
//    }


    @Override
    public void removeOneProduct(String productName) {
        for (Iterator<Product> iterator = cartData.keySet().iterator(); iterator.hasNext();){
            Product product = iterator.next();
            if (productName.equals(product.getName())) {
                if (cartData.get(product) <= 1) {
                    iterator.remove();
                } else {
                    cartData.put(product, cartData.get(product) - 1);
                }
            }
        }
    }


    @Override
    public void emptyCart() {
        cartData.clear();
    }

    @Override
    public void addProductToShoppingCart(Integer id) {

        Product product = productDataStore.find(id);
        if (cartData.containsKey(product)) {
            cartData.put(product, cartData.get(product) + 1);
        } else {
            cartData.put(product, 1);
        }
    }


    @Override
    public HashMap<Product, Integer> getAll() {
        return cartData;
    }


    @Override
    public int getCarDataSize() {
        int count = 0;
        for (Integer value : cartData.values()) {
            count += value;
        }
       return count;

    }

    @Override
    public float getTotalPrice() {
        return cartData.keySet().stream().map(key -> key.getDefaultPrice() * cartData.get(key)).reduce((float) 0, (x, y) -> x + y);
    }
}


