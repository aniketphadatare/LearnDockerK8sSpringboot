package com.example.apigatewayservice.controller;

import com.example.apigatewayservice.model.Product;
import com.example.apigatewayservice.serviceinterfaces.CustomerService;
import com.example.apigatewayservice.serviceinterfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/login")
    @PreAuthorize("hasRole('ROLE_customer')")
    public ResponseEntity<String> getLogin() {
        return ResponseEntity.ok("Hello Login");
    }

    @GetMapping("/homepage")
    public ResponseEntity<String> getHomePage() {
        return ResponseEntity.ok("Hello homepage");
    }

    @GetMapping("/Customer")
    public ResponseEntity<String> getCustomer() {
        return ResponseEntity.ok("Hello Customer");
    }

    @GetMapping("/Seller")
    public ResponseEntity<String> getSeller() {
        return ResponseEntity.ok("Hello Seller");
    }
}
