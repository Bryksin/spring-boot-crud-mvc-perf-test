-- liquibase formatted sql
-- changeset Vadim Bryksin:init dbms:PostgreSQL

CREATE TABLE IF NOT EXISTS product (
    product_id serial PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR(1000),
    product_price DECIMAL(10,2) NOT NULL,
    product_stock INT NOT NULL DEFAULT 0
);
