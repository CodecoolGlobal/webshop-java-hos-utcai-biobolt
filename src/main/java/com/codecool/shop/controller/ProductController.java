package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDaoDataStore = CartDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Product> filteredProductsByCategory = new ArrayList<>();

        int categoryId;
        String categoryIdString = req.getParameter("categories");
        if (categoryIdString == null)
            categoryIdString = "all";
        if (categoryIdString.equals("all")){
            //IF ALL SELECTED (OR DEFAULT)
            context.setVariable("categoryName", "All products");
            filteredProductsByCategory = productDataStore.getAll();
        } else {
            //IF CATEGORY SELECTED
            try {
                categoryId = Integer.parseInt(req.getParameter("categories"));
                ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
                context.setVariable("categoryName", selectedCategory.getName());
                filteredProductsByCategory = productDataStore.getBy(selectedCategory);
                params.put("category", productCategoryDataStore.find(categoryId));
            }
            catch (Exception e){
                System.err.println("asd1");
            }
        }

        List<Product> filteredProductsBySupplier = new ArrayList<>();

        int supplierId;
        String supplierIdString = req.getParameter("suppliers");
        if (supplierIdString == null)
            supplierIdString = "all";
        if (supplierIdString.equals("all")){
            context.setVariable("supplierName", "all");
            filteredProductsBySupplier = productDataStore.getAll();
        } else {

            try {
                supplierId = Integer.parseInt(req.getParameter("suppliers"));
                Supplier selectedSupplier = productSupplierDataStore.find(supplierId);
                context.setVariable("supplierName", selectedSupplier.getName());
                filteredProductsBySupplier = productDataStore.getBy(selectedSupplier);
            } catch (Exception e){
                System.err.println(supplierIdString);
                System.err.println("asd2");
            }
        }

        List<Product> filteredProductsByAll = new ArrayList<>();
        try {
            for (Product product: filteredProductsByCategory){
                if (filteredProductsBySupplier.contains(product)){
                    filteredProductsByAll.add(product);
                }
            }
        } catch (Exception e){
            System.err.println(e);
        }

        context.setVariable("products", filteredProductsByAll);
        context.setVariable("suppliers", productSupplierDataStore.getAll());
        try {
            context.setVariable("categories", productCategoryDataStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("numberofcartitem", cartDaoDataStore.getCarDataSize());
        context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());




    }

}
