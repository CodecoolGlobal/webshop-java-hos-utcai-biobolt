package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier escobar = new Supplier("Pablo Escobar", "Old but gold products, from El Doctor");
        supplierDataStore.add(escobar);
        Supplier littleJohnny = new Supplier("Little Johnny", "Low prices at the expense of quality");
        supplierDataStore.add(littleJohnny);
        Supplier ilcsiNeni = new Supplier("Ilcsi néni", "Natural & Organic!");
        supplierDataStore.add(ilcsiNeni);

        //setting up a new product category
        ProductCategory reform = new ProductCategory("Reform substances", "Supplements", "New kinds of ");
        productCategoryDataStore.add(reform);
        ProductCategory classic = new ProductCategory("Old but gold", "Supplements", "Old but gold products");
        productCategoryDataStore.add(classic);
        ProductCategory starterPack = new ProductCategory("Starter Pack", "Supplements", "For the beginners, stepping into the game");
        productCategoryDataStore.add(starterPack);
        ProductCategory natural = new ProductCategory("Natural substances", "Supplements", "The soft breath of nature");
        productCategoryDataStore.add(natural);

        //setting up products and printing it
        productDataStore.add(new Product("Hypno Toad", 23, "USD", "Fantastic price. ALL GLORY TO THE HYPNOTOAD!", natural, littleJohnny));
        productDataStore.add(new Product("Bull Ball", 123, "USD", "The testicles of the mightiest of them all.", classic, escobar));
        productDataStore.add(new Product("Button Mushrooms", 5, "USD", "A pack of tasty titbits for uber-beginners.", starterPack, ilcsiNeni));
        productDataStore.add(new Product("Herbal", 3, "USD", "Your ticket to the ER!", reform, littleJohnny));
        productDataStore.add(new Product("Krokodil", 1, "USD", "Flesh-eating fun, which would prostrate Crocodile Dundee himself!", reform, littleJohnny));
        productDataStore.add(new Product("Ariel Pod", 13, "USD", "Looks like candy, tastes like soap, feels like instant gastric lavage.", reform, littleJohnny));
        productDataStore.add(new Product("Opium", 57, "USD", "It was good enough for the Greeks, Romans, Egyptians, Assyrians and Sumerians. It will be good enuogh for you too.", classic, escobar));
        productDataStore.add(new Product("Sage", 18, "USD", "Drives out evil inside and out.", classic, ilcsiNeni));
        productDataStore.add(new Product("Coca Leaf", 32, "USD", "The worlds most loved soda was created with coca leaf in mind. If you know what I mean...", natural, ilcsiNeni));
        productDataStore.add(new Product("Technokol Rapid", 6, "USD", "Multi-purpose tool for gluing and blooming.", reform, littleJohnny));
        productDataStore.add(new Product("The neighbours grass", 1, "USD", "It's always greener than yours.", starterPack, ilcsiNeni));
        productDataStore.add(new Product("Glandula Pinealis", 675, "USD", "Real delicacy, straight from your brain cells", natural, escobar));


    }
}
