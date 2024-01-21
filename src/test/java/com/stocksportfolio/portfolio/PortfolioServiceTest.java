package com.stocksportfolio.portfolio;

import com.stocksportfolio.portfolio.exception.UserNotFoundException;
import com.stocksportfolio.portfolio.model.Portfolio;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.model.User;
import com.stocksportfolio.portfolio.repository.PortfolioRepository;
import com.stocksportfolio.portfolio.repository.UserRepository;
import com.stocksportfolio.portfolio.response.PortfolioResponse;
import com.stocksportfolio.portfolio.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculatePortfolio_userNotFound_shouldThrowException() {
        Long userAccountId = 1L;
        when(userRepository.findById(userAccountId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> portfolioService.calculatePortfolio(userAccountId));

        verify(portfolioRepository, never()).findByUser(any());
    }

    @Test
    void calculatePortfolio_emptyPortfolio_shouldReturnZeroValues() {
        Long userAccountId = 1L;
        User user = new User();
        when(userRepository.findById(userAccountId)).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUser(user)).thenReturn(new ArrayList<>());

        PortfolioResponse result = portfolioService.calculatePortfolio(userAccountId);

        assertNotNull(result);
        assertNotNull(result.getHoldings());
        assertEquals(0, result.getHoldings().size());
        assertEquals(0.0, result.getTotalBuyPrice());
        assertEquals(0.0, result.getTotalPortfolioHolding());
        assertEquals("0.00%", result.getTotalPLPercentage());
    }

    @Test
    void calculatePortfolio_portfolioWithHoldings_shouldReturnCorrectValues() {
        Long userAccountId = 1L;
        User user = new User();
        when(userRepository.findById(userAccountId)).thenReturn(Optional.of(user));

        UpdateStocks stock1 = getStock1();

        UpdateStocks stock2 = getStock2();


        Portfolio portfolio1 = new Portfolio();
        portfolio1.setQuantity(5);
        portfolio1.setBuyPrice(99.06);
        portfolio1.setStock(stock1);

        Portfolio portfolio2 = new Portfolio();
        portfolio2.setQuantity(5);
        portfolio2.setBuyPrice(97.4);
        portfolio2.setStock(stock2);

        List<Portfolio> portfolios = List.of(portfolio1, portfolio2);
        when(portfolioRepository.findByUser(user)).thenReturn(portfolios);

        PortfolioResponse result = portfolioService.calculatePortfolio(userAccountId);

        assertNotNull(result);
        assertNotNull(result.getHoldings());
        assertEquals(2, result.getHoldings().size());
    }

    private static UpdateStocks getStock2() {
        UpdateStocks stock2 = new UpdateStocks();
        stock2.setId(15L);
        stock2.setClose(97.48);
        stock2.setHigh(97.48);
        stock2.setIsin("IN002023Y342");
        stock2.setLast(97.48);
        stock2.setLow(97.4);
        stock2.setOpen(97.4);
        stock2.setPrevClose(97.4);
        stock2.setSeries("TB");
        stock2.setSymbol("182D160524");
        stock2.setTimestamp("08-JAN-2024");
        stock2.setTotalTradedQuantity(600L);
        stock2.setTotalTradedValue(58472.0);
        stock2.setTotalTrades(3L);
        return stock2;
    }

    private static UpdateStocks getStock1() {
        UpdateStocks stock1 = new UpdateStocks();
        stock1.setId(17L);
        stock1.setClose(99.06);
        stock1.setHigh(99.06);
        stock1.setIsin("IN002023Y227");
        stock1.setLast(99.06);
        stock1.setLow(99.06);
        stock1.setOpen(99.06);
        stock1.setPrevClose(99.06);
        stock1.setSeries("TB");
        stock1.setSymbol("182D220224");
        stock1.setTimestamp("08-JAN-2024");
        stock1.setTotalTradedQuantity(200L);
        stock1.setTotalTradedValue(19812.0);
        stock1.setTotalTrades(1L);
        return stock1;
    }
}
