package com.stocksportfolio.portfolio.repository;

import com.stocksportfolio.portfolio.model.Portfolio;
import com.stocksportfolio.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUser(User user);
}
