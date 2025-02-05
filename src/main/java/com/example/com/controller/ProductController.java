package com.example.com.controller;


import com.example.com.dto.ProductDataConnector;
import com.example.com.response.MainResponse;
import com.example.com.service.ProductService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping
    public MainResponse createProduct(@RequestBody ProductDataConnector productDataConnector) {


        return productService.createProduct(productDataConnector);


    }

    @GetMapping
    public MainResponse getAllProduct()
    {
        return productService.getAllProduct();
    }


    @PutMapping("/update/{id}")
    public MainResponse updateById ( @PathVariable long id, @RequestBody ProductDataConnector productDataConnector)
    {

        return productService.updateById(id, productDataConnector);
    }

    @PutMapping("/isDelete/{id}")
    public MainResponse deleteByIDSetTrue ( @PathVariable long id)
    {
        return productService.isDeleteTrue(id);
    }
    @DeleteMapping("/delete/{id}")
    public MainResponse deleteById ( @PathVariable long id)
    {
        return productService.Delete(id);
    }





}
