//package com.stocksportfolio.portfolio.service;
//
//import com.stocksportfolio.portfolio.exception.StockNotFoundException;
//import com.stocksportfolio.portfolio.model.Trade;
//import com.stocksportfolio.portfolio.model.TradeType;
//import com.stocksportfolio.portfolio.model.UpdateStocks;
//import com.stocksportfolio.portfolio.repository.TradeRepository;
//import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
//import com.stocksportfolio.portfolio.response.TradeResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class TradeService {
//
//    @Autowired
//    private TradeRepository tradeRepository;
//
//    @Autowired
//    private UpdateStocksRepository updateStocksRepository;
//
//    @Transactional
//    public TradeResponse executeTrade(Long userAccountId, TradeType tradeType, Integer quantity, Long stockId) {
//        try {
//            UpdateStocks stock = updateStocksRepository.findById(stockId)
//                    .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + stockId));
//
//            Trade trade = new Trade();
//            trade.setUserAccountId(userAccountId);
//            trade.setTradeType(tradeType);
//            trade.setQuantity(quantity);
//            trade.setStock(stock);
//
//            tradeRepository.save(trade);
//
//            return new TradeResponse("Success", "Trade executed successfully");
//        } catch (StockNotFoundException e) {
//            return new TradeResponse("Failure", "Stock not found: " + e.getMessage());
//        } catch (DataAccessException e) {
//            return new TradeResponse("Failure", "Database error: " + e.getMessage());
//        } catch (Exception e) {
//            return new TradeResponse("Failure", "Unexpected error: " + e.getMessage());
//        }
//    }
//}

package com.stocksportfolio.portfolio.service;

import com.stocksportfolio.portfolio.exception.StockNotFoundException;
import com.stocksportfolio.portfolio.exception.UserNotFoundException;
import com.stocksportfolio.portfolio.model.*;
import com.stocksportfolio.portfolio.repository.*;
import com.stocksportfolio.portfolio.response.TradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UpdateStocksRepository updateStocksRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserTradeRepository userTradeRepository;

    @Transactional
    public TradeResponse executeTrade(Long userAccountId, TradeType tradeType, Integer quantity, Long stockId) {
        try {
            UpdateStocks stock = updateStocksRepository.findById(stockId)
                    .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + stockId));

            User user = userRepository.findById(userAccountId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userAccountId));

            Trade trade = new Trade();
            trade.setUserAccountId(userAccountId);
            trade.setTradeType(tradeType);
            trade.setQuantity(quantity);
            trade.setStock(stock);

            tradeRepository.save(trade);

            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolio.setStock(stock);
            portfolio.setQuantity(quantity);
            portfolio.setBuyPrice(stock.getOpen());
            portfolio.setTotalBuyPrice(quantity * stock.getOpen());

            portfolioRepository.save(portfolio);

            // Create UserTrade entry
            UserTrade userTrade = new UserTrade();
            userTrade.setUser(user);
            userTrade.setTrade(trade);
            userTrade.setUserAccountId(userAccountId);

            userTradeRepository.save(userTrade);

            Set<Trade> userTrades = user.getTrades();
            if (userTrades == null) {
                userTrades = new HashSet<>();
            }
            userTrades.add(trade);
            user.setTrades(userTrades);
            userRepository.save(user);

            return new TradeResponse("Success", "Trade executed successfully");
        } catch (StockNotFoundException e) {
            return new TradeResponse("Failure", "Stock not found: " + e.getMessage());
        } catch (UserNotFoundException e) {
            return new TradeResponse("Failure", "User not found: " + e.getMessage());
        } catch (DataAccessException e) {
            return new TradeResponse("Failure", "Database error: " + e.getMessage());
        } catch (Exception e) {
            return new TradeResponse("Failure", "Unexpected error: " + e.getMessage());
        }
    }
}

