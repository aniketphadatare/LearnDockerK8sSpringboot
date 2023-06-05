package com.example.apigatewayservice.serviceinterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customer-api-service", url = "customer-api-service:8090")
public interface CustomerService {

    @RequestMapping(value = "/Customer/{customerName}", method = RequestMethod.GET)
    boolean isValidCustomer(@PathVariable("customerName") String customerName);
}
