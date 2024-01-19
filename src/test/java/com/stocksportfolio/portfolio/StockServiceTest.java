//package com.stocksportfolio.portfolio;
//
//import com.stocksportfolio.portfolio.controller.StockController;
//import com.stocksportfolio.portfolio.model.UpdateStocks;
//import com.stocksportfolio.portfolio.repository.StockRepository;
//import com.stocksportfolio.portfolio.response.StockResponse;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class StockServiceTest {
//
//    @Mock
//    private StockRepository stockRepository;
//
//    @InjectMocks
//    private StockController stockDetailsController;
//
//    @Test
//    public void testGetStockDetails_Success() {
//        UpdateStocks mockStock = new UpdateStocks();
//        mockStock.setId(1L);
//        mockStock.setSymbol("ABC");
//        mockStock.setOpen(100.0);
//        mockStock.setClose(105.0);
//        mockStock.setHigh(110.0);
//        mockStock.setLow(95.0);
//
//        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(mockStock));
//
//
//        ResponseEntity<StockResponse> responseEntity = stockDetailsController.getStockDetails(1L);
//
//        // Assert the response
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        StockResponse responseBody = responseEntity.getBody();
//        assert responseBody != null;
//        assertEquals(1L, responseBody.getStockId());
//        assertEquals("ABC", responseBody.getSymbol());
//        assertEquals(100.0, responseBody.getOpen());
//        assertEquals(105.0, responseBody.getClose());
//        assertEquals(110.0, responseBody.getHigh());
//        assertEquals(95.0, responseBody.getLow());
//    }
//
//    @Test
//    public void testGetStockDetails_NotFound() {
//        // Mock repository response
//        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Call the controller method
//        ResponseEntity<StockResponse> responseEntity = stockDetailsController.getStockDetails(1L);
//
//        // Assert the response
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }
//}
