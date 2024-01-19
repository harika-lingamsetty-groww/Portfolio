//package com.stocksportfolio.portfolio;
//
//import com.stocksportfolio.portfolio.exception.StockNotFoundException;
//import com.stocksportfolio.portfolio.model.Trade;
//import com.stocksportfolio.portfolio.model.TradeType;
//import com.stocksportfolio.portfolio.model.UpdateStocks;
//import com.stocksportfolio.portfolio.repository.TradeRepository;
//import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
//import com.stocksportfolio.portfolio.response.TradeResponse;
//import com.stocksportfolio.portfolio.service.TradeService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class TradeServiceTest {
//
//    @Mock
//    private TradeRepository tradeRepository;
//
//    @Mock
//    private UpdateStocksRepository updateStocksRepository;
//
//    @InjectMocks
//    private TradeService tradeService;
//
//    @Test
//    public void testExecuteTrade_Success() {
//        // Mocking the repository responses
//        when(updateStocksRepository.findById(anyLong()))
//                .thenReturn(java.util.Optional.of(new UpdateStocks()));
//        when(tradeRepository.save(any(Trade.class))).thenReturn(new Trade());
//
//        // Call the service method
//        TradeResponse tradeResponse = tradeService.executeTrade(1L, TradeType.BUY, 10, 123L);
//
//        // Assert the response
//        assertEquals("Success", tradeResponse.getStatus());
//        assertEquals("Trade executed successfully", tradeResponse.getMessage());
//    }
//
//    @Test
//    public void testExecuteTrade_Failure_StockNotFound() {
//        // Mocking the repository response for StockNotFoundException
//        when(updateStocksRepository.findById(anyLong()))
//                .thenThrow(new StockNotFoundException("Stock not found with id: 123"));
//
//        // Call the service method
//        TradeResponse tradeResponse = tradeService.executeTrade(1L, TradeType.BUY, 10, 123L);
//
//        // Assert the response
//        assertEquals("Failure", tradeResponse.getStatus());
//        assertEquals("Stock not found with id: 123", tradeResponse.getMessage());
//    }
//}
