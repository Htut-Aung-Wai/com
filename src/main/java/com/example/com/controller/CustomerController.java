package com.example.com.controller;


import com.example.com.dto.CustomerDataConnector;
import com.example.com.dto.ProductDataConnector;
import com.example.com.response.MainResponse;
import com.example.com.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public MainResponse createProduct(@RequestBody CustomerDataConnector customerDataConnector) {


        return customerService.createCustomer(customerDataConnector);


    }

    @GetMapping
    public MainResponse getAllProduct()
    {
        return customerService.getAllProduct();
    }

    @GetMapping("/{id}")
    public MainResponse getByCustomerId(@PathVariable long id)
    {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public MainResponse updateById ( @PathVariable long id, @RequestBody CustomerDataConnector customerDataConnector)
    {

        return customerService.updateById(id, customerDataConnector);
    }

    @PutMapping("/status/{id}")
    public MainResponse statusToDisable( @PathVariable long id)
    {
        return customerService.updateStatus(id);
    }
    @DeleteMapping("/{id}")
    public MainResponse deleteById ( @PathVariable long id)
    {
        return customerService.Delete(id);
    }
}
