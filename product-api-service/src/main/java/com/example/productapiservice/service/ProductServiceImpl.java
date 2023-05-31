package com.example.productapiservice.service;

import com.example.productapiservice.exceptionhandling.ProductNotFoundException;
import com.example.productapiservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class ProductServiceImpl implements ProductService{
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private HashMap<Long, Product> productList = new HashMap<Long, Product>();

    @Autowired
    private WebClient webClient;

    @Override
    public void createProduct(Product product) {
        boolean isValidCustomer = webClient.get()
                .uri("http://customer-api-service:8090/Customer/"+product.getCustomerName())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if(isValidCustomer) {
            productList.put(product.getId(), product);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public String updateProduct(Long id, Product product) {
        if(productList.get(id) != null) {
            productList.put(product.getId(), product);
            return "Successfully updated";
        } else {
            logger.info("updateRecord not found");
            return "Update record failed";
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productList.remove(id);
    }

    @Override
    public HashMap<Long, Product> getProducts() {
        return productList;
    }

    @Override
    public Product getProducts(Long id) {
        return productList.get(id);
    }

    @Override
    public Long getProductCount() {
        return Long.getLong(String.valueOf(productList.size()));
    }

}
