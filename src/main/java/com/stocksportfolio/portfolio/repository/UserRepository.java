package com.stocksportfolio.portfolio.repository;

import com.stocksportfolio.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}