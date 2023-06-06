package com.example.apigatewayservice.controller;

import com.example.apigatewayservice.model.Product;
import com.example.apigatewayservice.serviceinterfaces.CustomerService;
import com.example.apigatewayservice.serviceinterfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiGWController {

    Logger logger = LoggerFactory.getLogger(ApiGWController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @PostMapping("/v1/createProduct")
    String createProduct(@RequestBody Product product){
        logger.info("createProduct");
        return productService.createProduct(product);
    }

    @GetMapping("/ValidCustomer/{customerName}")
    boolean isValidCustomer(@PathVariable("customerName") String customerName) {
       return customerService.isValidCustomer(customerName);
    }
}
