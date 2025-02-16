package com.example.com.service;


import com.example.com.dto.ProductDataConnector;
import com.example.com.entity.Product;
import com.example.com.repository.ProductRepository;
import com.example.com.response.MainResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Locale;


@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    private MainResponse mainResponse;

    private final String success;
    private final String failed;
    private final String notFound;

    // Usage of local for i18n
    private final MessageSource messageSource;
    Locale locale = new Locale("my");



    public ProductService(ProductRepository productRepository, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.messageSource=messageSource;
        success = messageSource.getMessage("success", null, locale);
        failed = messageSource.getMessage("failed", null, locale);
        notFound=messageSource.getMessage("Not_Found",null,locale);

    }



    //Create Product
    public MainResponse createProduct(ProductDataConnector dco) {

        //check for stock and price valid or not
        if(dco.getStock()==0)
        {
            return new MainResponse(failed,null);
        }
        if(dco.getPrice()==0)
        {
            return new MainResponse(failed,null);
        }


        Product product = new Product(dco.getName(), dco.getCategory(), dco.getStock(), dco.getPrice(),false);
        return new MainResponse(success, productRepository.save(product));


    }


    //get all
    public MainResponse getAllProduct()
    {
        return new MainResponse(success,productRepository.findAll());
    }

    //get by id
    public MainResponse getProductById(long id){

        return new MainResponse(success,productRepository.findById(id));
    }

    //update by id
    public MainResponse updateById(long id,ProductDataConnector dco)
    {

        //check for stock and price valid or not
        if(dco.getStock()==0)
        {
            return new MainResponse(failed,null);
        }
        if(dco.getPrice()==0)
        {
            return new MainResponse(failed,null);
        }

        //Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(failed));
        if(productRepository.existsById(id)) {
            Product product=new Product(dco.getName(),dco.getCategory(),dco.getStock(),dco.getPrice(),false);
            product.setId(id);
            //product.setName(dco.getName());
            //product.setCategory(dco.getCategory());
            //product.setStock(dco.getStock());
            //product.setPrice(dco.getPrice());

            return new MainResponse(success, productRepository.save(product));
        }

        else {

            return new MainResponse(notFound,null);
        }


    }

    //set is delete to true
    @Transactional
    public MainResponse isDeleteTrue(long id)
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(notFound));
        product.setIsDelete(true);
        return new MainResponse(success,productRepository.save(product));


    }

    // delete
    public  MainResponse Delete(long id)
    {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new MainResponse(success, null);
        }
        else return new MainResponse(notFound,null);
    }









}
