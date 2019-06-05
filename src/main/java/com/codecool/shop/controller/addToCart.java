package com.codecool.shop.controller;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoDB;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addToCart"})
public class addToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        CartDao cartDaoDataStore = CartDaoMem.getInstance();

        Integer addId = Integer.parseInt(req.getParameter("product_id"));
        // context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(addId))));
        cartDaoDataStore.addProductToShoppingCart(addId);
        resp.sendRedirect("/");
    }
}
