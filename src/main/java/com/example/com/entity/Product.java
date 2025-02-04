package com.example.com.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryEnum category;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "isDelete", nullable = false)
    private boolean isDelete;


    public Product()
    {

    }
    public Product(String name, CategoryEnum category, int stock, double price,boolean isDelete) {

        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.isDelete=isDelete;

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }
}


