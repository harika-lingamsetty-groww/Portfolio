package com.stocksportfolio.portfolio.repository;

import com.stocksportfolio.portfolio.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
