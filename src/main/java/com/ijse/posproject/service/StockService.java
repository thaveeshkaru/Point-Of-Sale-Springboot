package com.ijse.posproject.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.Stock;

@Service
public interface StockService {
    Stock createStock(Stock stock);
    List<Stock> getAllStocks();
    Stock updateStock(Long stockId, Stock stock);
    void deleteStock(Long stockId);
    List<Stock> getStocksByItemCode(Long itemCode);
    Stock getStockByStockId(Long stockId);
}
