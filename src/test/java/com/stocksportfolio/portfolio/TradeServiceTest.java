package com.stocksportfolio.portfolio;
import com.stocksportfolio.portfolio.model.*;
import com.stocksportfolio.portfolio.repository.PortfolioRepository;
import com.stocksportfolio.portfolio.repository.TradeRepository;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import com.stocksportfolio.portfolio.repository.UserRepository;
import com.stocksportfolio.portfolio.repository.UserTradeRepository;
import com.stocksportfolio.portfolio.response.TradeResponse;
import com.stocksportfolio.portfolio.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private UpdateStocksRepository updateStocksRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private UserTradeRepository userTradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeTrade_stockNotFound_shouldReturnFailureResponse() {
        Long userAccountId = 1L;
        TradeType tradeType = TradeType.BUY;
        Integer quantity = 5;
        Long stockId = 1L;

        when(updateStocksRepository.findById(stockId)).thenReturn(Optional.empty());

        TradeResponse result = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);

        assertEquals("Failure", result.getStatus());
        assertEquals("Stock not found: Stock not found with id: 1", result.getMessage());

        verify(tradeRepository, never()).save(any());
        verify(portfolioRepository, never()).save(any());
        verify(userTradeRepository, never()).save(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void executeTrade_userNotFound_shouldReturnFailureResponse() {
        Long userAccountId = 1L;
        TradeType tradeType = TradeType.BUY;
        Integer quantity = 5;
        Long stockId = 1L;

        when(updateStocksRepository.findById(stockId)).thenReturn(Optional.of(new UpdateStocks()));
        when(userRepository.findById(userAccountId)).thenReturn(Optional.empty());

        TradeResponse result = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);

        assertEquals("Failure", result.getStatus());
        assertEquals("User not found: User not found with id: 1", result.getMessage());

        verify(tradeRepository, never()).save(any());
        verify(portfolioRepository, never()).save(any());
        verify(userTradeRepository, never()).save(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void executeTrade_successfulTrade_shouldReturnSuccessResponse() {
        Long userAccountId = 1L;
        TradeType tradeType = TradeType.BUY;
        Integer quantity = 5;
        Long stockId = 1L;

        UpdateStocks stock = new UpdateStocks();
        stock.setId(stockId);
        stock.setSymbol("AAPL");
        stock.setOpen(150.0);

        User user = new User();
        user.setUserId(userAccountId);
        user.setUsername("user");

        when(updateStocksRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(userRepository.findById(userAccountId)).thenReturn(Optional.of(user));

        TradeResponse result = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);

        assertEquals("Success", result.getStatus());
        assertEquals("Trade executed successfully", result.getMessage());

        verify(tradeRepository, times(1)).save(any());
        verify(portfolioRepository, times(1)).save(any());
        verify(userTradeRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void executeTrade_databaseError_shouldReturnFailureResponse() {
        Long userAccountId = 1L;
        TradeType tradeType = TradeType.BUY;
        Integer quantity = 5;
        Long stockId = 1L;

        UpdateStocks stock = new UpdateStocks();
        stock.setId(stockId);
        stock.setSymbol("AAPL");
        stock.setOpen(150.0);

        User user = new User();
        user.setUserId(userAccountId);
        user.setUsername("user");

        when(updateStocksRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(userRepository.findById(userAccountId)).thenReturn(Optional.of(user));
        when(tradeRepository.save(any())).thenThrow(new RuntimeException("Database error"));

        TradeResponse result = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);

        assertEquals("Failure", result.getStatus());
        assertEquals("Unexpected error: Database error", result.getMessage());

        verify(tradeRepository, times(1)).save(any());
        verify(portfolioRepository, never()).save(any());
        verify(userTradeRepository, never()).save(any());
        verify(userRepository, never()).save(any());
    }

}
