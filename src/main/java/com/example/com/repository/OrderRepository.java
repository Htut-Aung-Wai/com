package com.example.com.repository;

import com.example.com.dto.OrderCollectionConnector;
import com.example.com.entity.Customer;
import com.example.com.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {



    // Custom query using @Query: Native SQL query for users by customerId
    @Query(value = "SELECT product_Id FROM orders WHERE customer_Id = :customerId", nativeQuery = true)
    long[] findProductId(@Param("customerId") long customerId);

    @Query(value = "SELECT quantity FROM orders WHERE customer_Id = :customerId", nativeQuery = true)
    int[] findQuantity(@Param("customerId") long customerId);


    boolean existsByCustomerId(long customerId);
}
