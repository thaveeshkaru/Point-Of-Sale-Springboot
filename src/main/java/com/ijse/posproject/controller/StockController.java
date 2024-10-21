package com.ijse.posproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posproject.dto.StockReqDto;
import com.ijse.posproject.entity.Item;
import com.ijse.posproject.entity.Stock;
import com.ijse.posproject.service.ItemService;
import com.ijse.posproject.service.StockService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
@EnableWebSecurity
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/stocks")
    public ResponseEntity<?> createStock(@RequestBody StockReqDto stockReqDto) {

        try {
            Stock newStock = new Stock();
            Item item = itemService.getItemById(stockReqDto.getItemCode());
            newStock.setItem(item);
            newStock.setQuantityOnHand(stockReqDto.getQuantityOnHand());
            newStock.setLocation(stockReqDto.getLocation());
            Stock addStock= stockService.createStock(newStock);
            return ResponseEntity.status(201).body(addStock);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.status(201).body(stocks);
    }

    @PutMapping("/stocks/{stockId}")
    public ResponseEntity<?> updateStock(@PathVariable Long stockId, @RequestBody StockReqDto stockReqDto) {
        try {
            Stock stock = new Stock();
            Item item = itemService.getItemById(stockReqDto.getItemCode());
            if(item!= null){
                stock.setItem(item);
                stock.setQuantityOnHand(stockReqDto.getQuantityOnHand());
                stock.setLocation(stockReqDto.getLocation());
                Stock updatedStock=stockService.updateStock(stockId,stock);
                return ResponseEntity.status(201).body(updatedStock);

            }else{
                return ResponseEntity.status(400).body(null);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        } 

    }

    @DeleteMapping("/stocks/{stockId}")
    public void deleteStock(@PathVariable Long stockId){
        stockService.deleteStock(stockId);
    }

    @GetMapping("/stocks/{itemCode}")
    public ResponseEntity<?> getStockById(@PathVariable Long itemCode) {
        List<Stock> stocks = stockService.getStocksByItemCode(itemCode);
        return ResponseEntity.status(201).body(stocks);
    }
    
    @GetMapping("/stocks/findbyid/{stockId}")
    public ResponseEntity<?> getStockByStockId(@PathVariable Long stockId) {
        Stock stock = stockService.getStockByStockId(stockId);
        return ResponseEntity.status(201).body(stock);
    }
    

}
