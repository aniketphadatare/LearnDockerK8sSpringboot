package com.example.apigatewayservice.model;

public class Product {

    private long id;

    private long version;

    private String productName;

    private String customerName;

    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String toString() {
        return "id: "+ id +" version:" + version + " productName:"+productName + " customerName:"+customerName;
    }
}
