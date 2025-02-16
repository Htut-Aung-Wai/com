package com.example.com.dto;

import jakarta.persistence.Column;

public class OrderDataConnector {



    private long customerId;

    private long productId;

    private int quantity;



    public OrderDataConnector(long customerId, long productId,int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity=quantity;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


