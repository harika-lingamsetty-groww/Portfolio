//package com.stocksportfolio.portfolio;
//
//import com.stocksportfolio.portfolio.controller.TradeController;
//import com.stocksportfolio.portfolio.model.TradeType;
//import com.stocksportfolio.portfolio.response.TradeResponse;
//import com.stocksportfolio.portfolio.service.TradeService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//public class TradeControllerTest {
//
//    @Mock
//    private TradeService tradeService;
//
//    @InjectMocks
//    private TradeController tradeController;
//
//    @Test
//    public void testExecuteTrade_Success() {
//        when(tradeService.executeTrade(anyLong(), any(), anyInt(), anyLong()))
//                .thenReturn(new TradeResponse("Success", "Trade executed successfully"));
//
//        // Call the controller method
//        ResponseEntity<TradeResponse> responseEntity = tradeController.executeTrade(1L, TradeType.BUY, 10, 123L);
//
//        // Assert the response
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals("Success", responseEntity.getBody().getStatus());
//        assertEquals("Trade executed successfully", responseEntity.getBody().getMessage());
//    }
//
//    @Test
//    public void testExecuteTrade_Failure() {
//        when(tradeService.executeTrade(anyLong(), any(), anyInt(), anyLong()))
//                .thenReturn(new TradeResponse("Failure", "Stock not found with id: 123"));
//
//        ResponseEntity<TradeResponse> responseEntity = tradeController.executeTrade(1L, TradeType.BUY, 10, 123L);
//
//        assertEquals(404, responseEntity.getStatusCodeValue());
//        assertEquals("Failure", responseEntity.getBody().getStatus());
//        assertEquals("Stock not found with id: 123", responseEntity.getBody().getMessage());
//    }
//}
