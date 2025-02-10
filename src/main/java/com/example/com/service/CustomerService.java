package com.example.com.service;


import com.example.com.dto.CustomerDataConnector;
import com.example.com.dto.ProductDataConnector;
import com.example.com.entity.Customer;
import com.example.com.entity.Product;
import com.example.com.repository.CustomerRepository;
import com.example.com.repository.ProductRepository;
import com.example.com.response.MainResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    private MainResponse mainResponse;

    private final String success;
    private final String failed;
    private final String notFound;

    // Usage of local for i18n
    private final MessageSource messageSource;
    Locale locale = new Locale("my");

    public CustomerService(CustomerRepository customerRepository, MessageSource messageSource) {
        this.customerRepository = customerRepository;
        this.messageSource = messageSource;
        success = messageSource.getMessage("success", null, locale);
        failed = messageSource.getMessage("failed", null, locale);
        notFound=messageSource.getMessage("Not_Found",null,locale);
    }

        // create customer

    public MainResponse createCustomer(CustomerDataConnector dco) {

        Customer customer=new Customer(dco.getName(),dco.getBalance(),Customer.StatusEnum.ACTIVE);
        return new MainResponse(success, customerRepository.save(customer));


    }

    //get all
    public MainResponse getAllProduct()
    {
        return new MainResponse(success,customerRepository.findAll());
    }


    //get by id
    public MainResponse getCustomerById(long id){
        if(customerRepository.findById(id).isPresent())
        {return new MainResponse(success,customerRepository.findById(id));}
        else return new MainResponse(notFound,null);
    }

    //update by id
    public MainResponse updateById(long id,CustomerDataConnector dco)
    {



        //Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(failed));
        if(customerRepository.existsById(id)) {
            Customer customer=new Customer(dco.getName(),dco.getBalance(),dco.getStatus());
            customer.setId(id);

            return new MainResponse(success, customerRepository.save(customer));
        }

        else {

            return new MainResponse(notFound,null);
        }


    }


    //change Status to Disabled
    @Transactional
    public MainResponse updateStatus(long id)
    {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException(notFound));
        customer.setStatus(Customer.StatusEnum.DISABLED);
        return new MainResponse(success,customerRepository.save(customer));


    }

    // delete
    public  MainResponse Delete(long id)
    {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return new MainResponse(success, null);
        }
        else return new MainResponse(notFound,null);
    }






}
