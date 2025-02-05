package com.example.com.dto;

import com.example.com.entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class CustomerDataConnector {


    private long id;

    private String name;

    private double balance;

    private Customer.StatusEnum status;


    public CustomerDataConnector(String name, double balance, Customer.StatusEnum status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
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

    public Customer.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Customer.StatusEnum status) {
        this.status = status;
    }
}
