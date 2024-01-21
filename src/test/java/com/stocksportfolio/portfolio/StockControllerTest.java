package com.stocksportfolio.portfolio;

import com.stocksportfolio.portfolio.controller.StockController;
import com.stocksportfolio.portfolio.exception.StockNotFoundException;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import com.stocksportfolio.portfolio.response.StockResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private UpdateStocksRepository updateStocksRepository;

    @Test
    public void getStockDetails_validStockId_shouldReturnStockResponse() {
        Long stockId = 7L;
        UpdateStocks mockStock = createMockStock();
        when(updateStocksRepository.findById(stockId)).thenReturn(Optional.of(mockStock));
        ResponseEntity<StockResponse> responseEntity = stockController.getStockDetails(stockId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        StockResponse response = responseEntity.getBody();
        assert response != null;
        assertEquals(mockStock.getId(), response.getStockId());
        assertEquals(mockStock.getSymbol(), response.getSymbol());
        assertEquals(mockStock.getOpen(), response.getOpen());
        assertEquals(mockStock.getClose(), response.getClose());
        assertEquals(mockStock.getHigh(), response.getHigh());
        assertEquals(mockStock.getLow(), response.getLow());
    }

    @Test
    public void getStockDetails_invalidStockId_shouldThrowStockNotFoundException() {
        Long stockId = 99L;
        when(updateStocksRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            stockController.getStockDetails(stockId);
        } catch (StockNotFoundException e) {
            assertEquals("Stock not found with id: " + stockId, e.getMessage());
        }
    }

    private UpdateStocks createMockStock() {
        UpdateStocks stock = new UpdateStocks();
        stock.setId(7L);
        stock.setSymbol("1018GS2026");
        stock.setOpen(127.0);
        stock.setClose(127.0);
        stock.setHigh(127.0);
        stock.setLow(127.0);
        return stock;
    }
}
