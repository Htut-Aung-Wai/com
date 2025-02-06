package com.example.com.controller;


import com.example.com.dto.OrderDataConnector;
import com.example.com.dto.ProductDataConnector;
import com.example.com.response.MainResponse;
import com.example.com.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public MainResponse createOrder(@RequestBody OrderDataConnector orderDataConnector) {


        return orderService.createOrder(orderDataConnector);


    }

    @GetMapping
    public MainResponse getAllProduct() {
        return orderService.getOrder();
    }

    // get order by customer id
    @GetMapping("customer/{customerId}")
    public MainResponse getByCustomerId(@PathVariable long customerId) {

        return orderService.getOrderByCustomerId(customerId);
    }

    @PutMapping("update/{orderId}")
    public MainResponse updateById(@PathVariable long orderId, @RequestBody OrderDataConnector odc) {

        return orderService.updateOrder(orderId, odc);
    }

    @DeleteMapping("delete/{orderId}")
    public MainResponse deleteByID(@PathVariable long orderId)
    {
        return orderService.deleteOrder(orderId);
    }

}
