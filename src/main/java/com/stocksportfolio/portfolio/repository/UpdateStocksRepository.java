package com.stocksportfolio.portfolio.repository;

import com.stocksportfolio.portfolio.model.UpdateStocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UpdateStocksRepository extends JpaRepository<UpdateStocks, Long> {
    Optional<UpdateStocks> findBySymbolAndTimestamp(String symbol, String timestamp);
}
