package com.example.com.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "balance", nullable = false)
    private double balance;
    public enum StatusEnum {

        ACTIVE,DISABLED
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public Customer(String name, double balance, StatusEnum status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public Customer()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
