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
import com.ijse.posproject.entity.Stock;
import com.ijse.posproject.service.ItemService;
import com.ijse.posproject.service.OrderService;
import com.ijse.posproject.service.StockService;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StockService stockService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDto orderReqDto){
        List<Long> itemCodes = orderReqDto.getItemCodes();
        
        Order order = new Order();
        order.setTotalPrice(0.0);
        List<Item> orderedItems = new ArrayList<>();

        itemCodes.forEach(itemCode ->{
            Item item = itemService.getItemById(itemCode);

            if(item != null){

                 List<Stock> stocks = stockService.getStocksByItemCode(itemCode);
                 boolean stockDeducted = false;
                 order.setPaymentMethod(orderReqDto.getPaymentMethod());
                 for (Stock stock : stocks) {
                    // Check if stock is available
                    if (stock.getQuantityOnHand() > 0) {
                        // Deduct stock
                        stock.setQuantityOnHand(stock.getQuantityOnHand() - 1);

                        stockService.updateStock(stock.getStockId(), stock); // Save updated stock
                        orderedItems.add(item); // Add item to order
                        order.setTotalPrice(order.getTotalPrice() + item.getPrice()); // Calculate total price
                        stockDeducted = true;
                        break; // Exit after deducting from the first available stock
                    }
                }

                if (!stockDeducted) {
                    throw new RuntimeException("Not enough stock for item: " + item.getItemCode());
                }      
            }
        });
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
