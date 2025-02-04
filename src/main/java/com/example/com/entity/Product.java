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
}
