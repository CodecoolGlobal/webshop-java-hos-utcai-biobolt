package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class CartDaoMem implements CartDao {

    ProductDao productDataStore = ProductDaoMem.getInstance();

    private HashMap<Product, Integer> cartData = new LinkedHashMap<>();
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
            cartData.put(product, cartData.get(product)+1);
        } else {
            cartData.put(product, 1);
        }

    }

    @Override
    public void removeOneProduct(Product product) {
        if (cartData.get(product) > 0) {
            cartData.put(product, cartData.get(product) - 1);
        } else {
            this.removeAllProduct(product);
        }
    }

    @Override
    public void removeAllProduct(Product product) {
        cartData.remove(product);
    }

    @Override
    public void emptyCart() {
        for (Product product : cartData.keySet()) {
            cartData.remove(product);
        }
    }

    @Override
    public HashMap<Product, Integer> getAll() {
//        Iterator it = cartData.entrySet().iterator();
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)
//        }
        return cartData;
        // TODO: 2019.05.22. implement getAll
    }


    public void addProductToShoppingCart(int productId){
        addOneProduct(productDataStore.find(productId));

    }

    @Override
    public int getSize() {
        return cartData.size();
    }


}
