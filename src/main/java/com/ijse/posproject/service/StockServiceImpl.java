package com.ijse.posproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.Stock;
import com.ijse.posproject.repository.StockRepository;


@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock updateStock(Long stockId, Stock stock) {
       Stock existingStock = stockRepository.findById(stockId).orElse(null);
       if (existingStock==null){
            return null;
       }else{
            existingStock.setItem(stock.getItem());
            existingStock.setQuantityOnHand(stock.getQuantityOnHand());
            existingStock.setLocation(stock.getLocation());
            return stockRepository.save(existingStock);
       }

    }

    @Override
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    @Override
    public List<Stock> getStocksByItemCode(Long itemCode) {
        return stockRepository.findByItem_ItemCode(itemCode);
    }

}
