package com.example.customer_api_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/Customer/{customerName}")
    public boolean isValidCustomer(@PathVariable("customerName") String customerName) {
        logger.info("isValidCustomer :" + customerName);
        if(customerName.equalsIgnoreCase("test"))
            return true;
        else
            return false;
    }
}
