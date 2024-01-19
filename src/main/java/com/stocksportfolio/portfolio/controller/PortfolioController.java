package com.stocksportfolio.portfolio.controller;

import com.stocksportfolio.portfolio.response.PortfolioResponse;
import com.stocksportfolio.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{userAccountId}")
    public ResponseEntity<PortfolioResponse> getPortfolio(@PathVariable Long userAccountId) {
        PortfolioResponse portfolioResponse = portfolioService.calculatePortfolio(userAccountId);
        return ResponseEntity.ok(portfolioResponse);
    }
}
