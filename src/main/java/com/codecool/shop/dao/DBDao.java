package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.sun.tools.javac.util.List;

public interface DBDao {

    void add(ProductCategory productCategory);
    void add(Product product);
    void add(Supplier supplier);

    Object find(int id);

    List<Object> getAll();

    void remove(int id);


}
