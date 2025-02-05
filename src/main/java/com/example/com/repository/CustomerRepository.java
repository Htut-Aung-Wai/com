package com.example.com.repository;

import com.example.com.entity.Customer;
import com.example.com.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
