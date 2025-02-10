package com.example.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "customerId", nullable = false)
    private long customerId;
    @Column(name = "productId", nullable = false)
    private long productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;

   /* @ManyToOne  // Many employees belong to one department
    @JoinColumn(name = "productId")  // Foreign key column in the employee table
    private Product product;*/



    public Order()
    {

    }

    public Order(long customerId, long productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
