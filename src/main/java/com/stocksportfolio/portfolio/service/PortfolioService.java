package com.stocksportfolio.portfolio.service;

import com.stocksportfolio.portfolio.exception.UserNotFoundException;
import com.stocksportfolio.portfolio.model.Portfolio;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.model.User;
import com.stocksportfolio.portfolio.repository.PortfolioRepository;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import com.stocksportfolio.portfolio.repository.UserRepository;
import com.stocksportfolio.portfolio.response.PortfolioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UpdateStocksRepository updateStocksRepository;

    public PortfolioResponse calculatePortfolio(Long userAccountId) {
        User user = userRepository.findById(userAccountId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userAccountId));

        List<Portfolio> portfolios = portfolioRepository.findByUser(user);
        PortfolioResponse response = new PortfolioResponse();

        List<PortfolioResponse.Holding> holdings = new ArrayList<>();
        double totalBuyPrice = 0.0;
        int totalQuantity = 0;
        double totalcurrentPrice = 0.0;

        for (Portfolio portfolio : portfolios) {
            totalBuyPrice += portfolio.getTotalBuyPrice();
            totalQuantity += portfolio.getQuantity();

            UpdateStocks stock = portfolio.getStock();
            if (stock != null) {
                totalcurrentPrice += stock.getLast();

                PortfolioResponse.Holding holding = getHolding(portfolio, stock);

                holdings.add(holding);
            }
        }

        double averageBuyPrice = totalQuantity > 0 ? totalBuyPrice / totalQuantity : 0.0;

        response.setHoldings(holdings);
        response.setTotalBuyPrice(totalBuyPrice);
        response.setTotalPortfolioHolding(totalcurrentPrice * totalQuantity);
        response.setTotalPLandPLPercentage(totalcurrentPrice, totalBuyPrice);

        return response;
    }

    private static PortfolioResponse.Holding getHolding(Portfolio portfolio, UpdateStocks stock) {
        PortfolioResponse.Holding holding = new PortfolioResponse.Holding();
        holding.setStockId(stock.getId());
        holding.setSymbol(stock.getSymbol());
        holding.setQuantity(portfolio.getQuantity());
        holding.setBuyPrice(portfolio.getBuyPrice());
        holding.setCurrentPrice(stock.getLast());
        holding.setGainLoss(holding.getQuantity() * (holding.getCurrentPrice() - holding.getBuyPrice()));
        double gainLossPercentage = (holding.getGainLoss() / holding.getBuyPrice()) * 100;
        holding.setGainLossPercentage(String.format("%.2f%%", gainLossPercentage));
        return holding;
    }
}
