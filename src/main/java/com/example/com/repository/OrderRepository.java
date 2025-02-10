package com.example.com.repository;

import com.example.com.dto.OrderDataConnector;
import com.example.com.dto.OrderReturnWithCustomerId;
import com.example.com.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {



    // Custom query using @Query: Native SQL query for users by customerId
    // return by array
    @Query(value = "SELECT product_Id FROM orders WHERE customer_Id = :customerId", nativeQuery = true)
    long[] findProductId(@Param("customerId") long customerId);

    @Query(value = "SELECT quantity FROM orders WHERE customer_Id = :customerId", nativeQuery = true)
    int[] findQuantity(@Param("customerId") long customerId);

    // Custom query using @Query: Native SQL query for users by customerId
    //return by obj
    @Query(value = "SELECT p.id,p.name,o.quantity,p.price FROM orders o JOIN product p ON o.product_Id=p.id WHERE o.customer_Id = :customerId", nativeQuery = true)
    List<OrderReturnWithCustomerId> findByCustomerId(@Param("customerId") long customerId);


    boolean existsByCustomerId(long customerId);
}
