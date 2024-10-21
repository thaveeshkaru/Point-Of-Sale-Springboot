package com.ijse.posproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posproject.dto.OrderReqDto;
import com.ijse.posproject.entity.Item;
import com.ijse.posproject.entity.Order;
import com.ijse.posproject.service.ItemService;
import com.ijse.posproject.service.OrderService;

@RestController
@CrossOrigin(origins = "*")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    

@PostMapping("/orders")
public ResponseEntity<?> createOrder(@RequestBody OrderReqDto orderReqDto){
    List<Long> itemCodes = orderReqDto.getItemCodes();
    
    Order order = new Order();
    order.setTotalPrice(0.0);
    List<Item> orderedItems = new ArrayList<>();

    itemCodes.forEach(itemCode ->{
        Item item = itemService.getItemById(itemCode);

        if(item != null){
          orderedItems.add(item); // Add item to order
          order.setTotalPrice(order.getTotalPrice() + item.getPrice()); // Calculate total price                      
        }
    });
    order.setPaymentMethod(orderReqDto.getPaymentMethod());
    order.setDiscount(orderReqDto.getDiscount());
    order.setTotalPrice(order.getTotalPrice()-orderReqDto.getDiscount());
    order.setOrderedItems(orderedItems);
    orderService.createOrder(order);
    return ResponseEntity.status(201).body(order);
}

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.status(201).body(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
       Order order= orderService.getOrderById(orderId);
       return ResponseEntity.status(201).body(order);
    }
    
}
