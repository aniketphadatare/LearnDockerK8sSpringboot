package com.example.productapiservice.model;

public class Product {

    private long id;

    private long version;

    private String productName;

    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String toString() {
        return "id: "+ id +" version:" + version + " productName:"+productName;
    }
}
