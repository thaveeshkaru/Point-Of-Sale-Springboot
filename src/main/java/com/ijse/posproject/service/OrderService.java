package com.ijse.posproject.service;

import org.springframework.stereotype.Service;
import java.util.List;


import com.ijse.posproject.entity.Order;

@Service
public interface OrderService {

    Order createOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);

}
