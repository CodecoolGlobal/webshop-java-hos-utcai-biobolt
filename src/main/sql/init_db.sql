ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS pk_products_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.productCategory DROP CONSTRAINT IF EXISTS pk_productCategory_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;

DROP TABLE IF EXISTS public.products;
DROP SEQUENCE IF EXISTS public.products_id_seq;
CREATE TABLE  products (
    id serial NOT NULL,
    name VARCHAR(255),
    defaultPrice FLOAT,
    currencyString VARCHAR(3),
    description text,
    categoryId int,
    supplierId int
);

DROP TABLE IF EXISTS public.productCategory;
DROP SEQUENCE IF EXISTS public.productCategory_id_seq;
CREATE TABLE  productCategory (
    id serial NOT NULL,
    name VARCHAR(255),
    department text,
    description text
);

DROP TABLE IF EXISTS public.supplier;
DROP SEQUENCE IF EXISTS public.supplier_id_seq;
CREATE TABLE  supplier (
   id serial NOT NULL,
   name VARCHAR(255),
   description text
);

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_products_id PRIMARY KEY (id);

ALTER TABLE ONLY productCategory
    ADD CONSTRAINT pk_productCategory_id PRIMARY KEY (id);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (categoryId) REFERENCES productCategory(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplierId) REFERENCES supplier(id);