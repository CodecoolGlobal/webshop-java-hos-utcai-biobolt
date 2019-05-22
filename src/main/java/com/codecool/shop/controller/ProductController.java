package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        int categoryId;
        String categoryIdString = req.getParameter("categories");
        if (categoryIdString == "all" || categoryIdString == null){
            //IF ALL SELECTED (OR DEFAULT)
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        } else {
            //IF CATEGORY SELECTED
            try {
                categoryId = Integer.parseInt(req.getParameter("categories"));
                ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
                context.setVariable("category", selectedCategory);
                context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
        context.setVariable("categories", productCategoryDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
