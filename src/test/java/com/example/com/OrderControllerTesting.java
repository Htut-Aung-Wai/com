package com.example.com;


import com.example.com.controller.OrderController;
import com.example.com.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTesting {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private OrderService orderService;  // Mock OrderService (if needed)

    @InjectMocks
    private OrderController orderController;




    @Test
    public void getOrderByCustomerIdTest() throws Exception
    {
        long[] productId={3,1,1,6};

       /* int[] quantity={1,2,3};

        ArrayList<String> productName=new ArrayList<>();
        productName.add("Name: clothes | Price: 20.0 | Quantity: 1");
        OrderCollectionConnector orderCollectionConnector=new OrderCollectionConnector();
        orderCollectionConnector.setCustomerId(1);
        orderCollectionConnector.setProductId(productId);
        orderCollectionConnector.setQuantity(quantity);
        orderCollectionConnector.setTotalPrice(10.0);*/



       mockMvc.perform(get("/orders/customer/{customerId}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.productId").value(productId));



    }

}
