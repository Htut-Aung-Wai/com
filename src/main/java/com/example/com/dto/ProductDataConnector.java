package com.example.com.dto;

import com.example.com.entity.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ProductDataConnector {


    private String name;

    private CategoryEnum category;

    private int stock;

    private double price;




    public ProductDataConnector(String name, CategoryEnum category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
