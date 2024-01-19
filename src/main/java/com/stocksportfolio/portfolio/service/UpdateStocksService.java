package com.stocksportfolio.portfolio.service;

import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateStocksService {
        @Autowired
        private UpdateStocksRepository updateStocksRepository;

        public void updateStocks(List<UpdateStocks> newStocks) {
            for (UpdateStocks newStock : newStocks) {
                Optional<UpdateStocks> existingStock = updateStocksRepository
                        .findBySymbolAndTimestamp(newStock.getSymbol(), newStock.getTimestamp());

                if (existingStock.isPresent()) {
                    UpdateStocks stockToUpdate = existingStock.get();
                    updateExistingStock(stockToUpdate, newStock);
                    updateStocksRepository.save(stockToUpdate);
                } else {
                    updateStocksRepository.save(newStock);
                }
            }
        }

        private void updateExistingStock(UpdateStocks existingStock, UpdateStocks newStock) {
            existingStock.setOpen(newStock.getOpen());
            existingStock.setHigh(newStock.getHigh());
            existingStock.setLow(newStock.getLow());
            existingStock.setClose(newStock.getClose());
            existingStock.setLast(newStock.getLast());
            existingStock.setPrevClose(newStock.getPrevClose());
            existingStock.setTotalTradedQuantity(newStock.getTotalTradedQuantity());
            existingStock.setTotalTradedValue(newStock.getTotalTradedValue());
            existingStock.setTotalTrades(newStock.getTotalTrades());
        }
}
