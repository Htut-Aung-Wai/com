package com.example.com.service;


import com.example.com.dto.OrderCollectionConnector;
import com.example.com.dto.OrderDataConnector;
import com.example.com.dto.OrderReturnWithCustomerId;
import com.example.com.dto.ProductDataConnector;
import com.example.com.entity.Customer;
import com.example.com.entity.Order;
import com.example.com.entity.Product;
import com.example.com.repository.CustomerRepository;
import com.example.com.repository.OrderRepository;
import com.example.com.repository.ProductRepository;
import com.example.com.response.MainResponse;
import com.sun.tools.javac.Main;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    private MainResponse mainResponse;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    private final String success;
    private final String failed;
    private final String outOfStock;
    private final String lowBalance;
    private final String notFound;

    private double totalPrice=0.0;
    private final ArrayList<String> productName=new ArrayList<>();



    // Usage of local for i18n
    private final MessageSource messageSource;
    Locale locale = new Locale("my");


    public OrderService(OrderRepository orderRepository, MessageSource messageSource,ProductRepository productRepository,CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.messageSource=messageSource;
        this.productRepository=productRepository;
        this.customerRepository=customerRepository;
        success = messageSource.getMessage("success", null, locale);
        failed = messageSource.getMessage("failed", null, locale);
        outOfStock=messageSource.getMessage("Out_of_Stock",null,locale);
        lowBalance=messageSource.getMessage("Low_Balance",null,locale);
        notFound=messageSource.getMessage("Not_Found",null,locale);


    }

    //Create Order
    @Transactional
    public MainResponse createOrder(OrderDataConnector odc)
    {

        Product product = productRepository.findById(odc.getProductId()).orElseThrow(() -> new RuntimeException(notFound));

            //check if product quantity enough
            if(product.getStock()<odc.getQuantity() && odc.getQuantity()!=0)
            {
                return new MainResponse(outOfStock,null);
            }
            else {

                product.setStock(product.getStock()-odc.getQuantity());
                productRepository.save(product);
            }

        Customer customer = customerRepository.findById(odc.getCustomerId()).orElseThrow(() -> new RuntimeException(notFound));

            //check if customer balance is enough

        double productTotalPrice= odc.getQuantity()* product.getPrice();
            if(customer.getBalance()<productTotalPrice)
            {
                return new MainResponse(lowBalance,null);
            }
            else {

                customer.setBalance(customer.getBalance()-productTotalPrice);
                customerRepository.save(customer);
            }

        if(customerRepository.findById(odc.getCustomerId()).isPresent() && productRepository.findById(odc.getProductId()).isPresent())
        {
            Order order=new Order(odc.getCustomerId(),odc.getProductId(),odc.getQuantity());
            return new MainResponse(success,orderRepository.save(order));
        }
        else {

            return new MainResponse(failed,null);
        }
    }




    //get order
    public MainResponse getOrder()
    {

        return new MainResponse(success,orderRepository.findAll());
    }


    // get order by customer id (output with array)
    public MainResponse getOrderByCustomerId(long customerId){



        if(!orderRepository.existsByCustomerId(customerId))
        {
            return new MainResponse(notFound,null);
        }

        calculateTotalPrice(orderRepository.findProductId(customerId),orderRepository.findQuantity(customerId));
        OrderCollectionConnector finalOrder=new OrderCollectionConnector(customerId,orderRepository.findProductId(customerId),orderRepository.findQuantity(customerId),totalPrice,productName);

        return new MainResponse(success,finalOrder);





    }


    //get order by customer id (output with customer id repeat)
    public MainResponse getOrderByCustomerIdRepeat(long customerId)
    {

        if(!orderRepository.existsByCustomerId(customerId))
    {
        return new MainResponse(notFound,null);
    }
        double totalPrice=0.0;
        List<OrderReturnWithCustomerId> orderReturnWithCustomerId=orderRepository.findByCustomerId(customerId);
        for(OrderReturnWithCustomerId orderReturnWithCustomerIdOneItem:orderReturnWithCustomerId)
        {
            totalPrice+=orderReturnWithCustomerIdOneItem.getTotalPrice();
        }
        return new MainResponse("Customer Id is "+customerId+"| Total Price is "+totalPrice,orderRepository.findByCustomerId(customerId));
    }

    //calculate price for final order
    public void calculateTotalPrice(long[] productId, int[] quantity)
    {   this.totalPrice=0.0;
        this.productName.clear();
        for (int i=0;i<productId.length;i++)
        {   Product product=productRepository.findById(productId[i]).orElseThrow(() -> new RuntimeException(notFound));
            totalPrice=(product.getPrice()*quantity[i])+totalPrice;

            this.productName.add("Name: "+product.getName()+" | Price: "+product.getPrice()+" | Quantity: "+quantity[i]);
        }

    }

    //update order

    public MainResponse updateOrder(long id,OrderDataConnector odc)

    {
        if(orderRepository.existsById(id)){
            Order order=new Order(odc.getCustomerId(),odc.getProductId(),odc.getQuantity());
            order.setId(id);
            return new MainResponse(success,orderRepository.save(order));
        }
        else {

            return new MainResponse(notFound,null);
        }

    }

    //delete order
    public MainResponse deleteOrder(long id)
    {

        if(orderRepository.existsById(id))
        {   orderRepository.deleteById(id);
            return new MainResponse(success,null);
        }
        else {

            return new MainResponse(notFound,null);
        }
    }








}
