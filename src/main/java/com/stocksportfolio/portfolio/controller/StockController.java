package com.stocksportfolio.portfolio.controller;

import com.stocksportfolio.portfolio.exception.StockNotFoundException;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import com.stocksportfolio.portfolio.response.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private UpdateStocksRepository updateStocksRepository;

    @GetMapping("/stock-details/{stockId}")
    public ResponseEntity<StockResponse> getStockDetails(@PathVariable Long stockId) {
        UpdateStocks stock = updateStocksRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + stockId));

        StockResponse response = new StockResponse();
        response.setStockId(stock.getId());
        response.setSymbol(stock.getSymbol());
        response.setPrices(stock.getOpen(), stock.getClose(), stock.getHigh(), stock.getLow());

        return ResponseEntity.ok(response);
    }
}
