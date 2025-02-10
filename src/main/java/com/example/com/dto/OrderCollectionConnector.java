package com.example.com.dto;

import java.util.ArrayList;

public class OrderCollectionConnector {

    private long customerId;

    private long[] productId;

    private int[] quantity;

    private double totalPrice;

    private ArrayList<String> productName=new ArrayList<>();

    public OrderCollectionConnector()
    {

    }


    public OrderCollectionConnector(long customerId, long[] productId, int[] quantity,double totalPrice,ArrayList<String> productName) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice=totalPrice;
        this.productName=productName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long[] getProductId() {
        return productId;
    }

    public void setProductId(long[] productId) {
        this.productId = productId;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public ArrayList<String> getProductName() {
        return productName;
    }

    public void setProductName(ArrayList<String> productName) {
        this.productName = productName;
    }





}
