package com.example.productapiservice.service;

import com.example.productapiservice.model.Product;

import java.util.HashMap;

public interface ProductService {
    public abstract void createProduct(Product product);
    public abstract String updateProduct(Long id, Product product);
    public abstract void deleteProduct(Long id);
    public abstract HashMap<Long, Product> getProducts();

    public Product getProducts(Long id);

    public Long getProductCount();
}
