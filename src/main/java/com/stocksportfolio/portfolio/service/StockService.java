//package com.stocksportfolio.portfolio.service;
//
//import com.stocksportfolio.portfolio.model.UpdateStocks;
//import com.stocksportfolio.portfolio.repository.StockRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StockService {
//
//    @Autowired
//    private StockRepository stockRepository;
//
//    public UpdateStocks getStockDetails(Long stockId) {
//        return stockRepository.findById(stockId).orElse(null);
//    }
//
//    public UpdateStocks getStockDetailsByStockId(String stockId) {
//        return stockRepository.findByStockId(stockId);
//    }
//}
