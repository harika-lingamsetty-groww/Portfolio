package com.stocksportfolio.portfolio.repository;

import com.stocksportfolio.portfolio.model.UserTrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTradeRepository extends JpaRepository<UserTrade, Long> {
}
