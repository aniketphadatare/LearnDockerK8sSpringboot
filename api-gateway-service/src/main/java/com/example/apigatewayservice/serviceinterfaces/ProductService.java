package com.example.apigatewayservice.serviceinterfaces;

import com.example.apigatewayservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-api-service", url = "product-api-service:8080")
public interface ProductService {

    @RequestMapping(value = "/Product", method = RequestMethod.POST)
    String createProduct(Product product);
}
