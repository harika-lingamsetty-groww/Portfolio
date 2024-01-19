package com.stocksportfolio.portfolio;

import com.opencsv.exceptions.CsvException;
import com.stocksportfolio.portfolio.controller.CsvParser;
import com.stocksportfolio.portfolio.repository.UpdateStocksRepository;
import com.stocksportfolio.portfolio.service.UpdateStocksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UpdateStocksServiceTest {

    @Autowired
    private UpdateStocksService updateStocksService;

    @Autowired
    private UpdateStocksRepository updateStocksRepository;

    private InputStream csvInputStream;

//    @BeforeEach
//    public void setup() throws IOException {
//        String csvContent = "SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN\n" +
//                "1018GS2026,GS,127,127,127,127,127,121,2,254,08-JAN-2024,2,IN0020010081\n" +
//                "182D020524,TB,97.71,97.75,97.67,97.67,97.67,97.75,45000,4395520,08-JAN-2024,18,IN002023Y326\n" +
//                "182D040724,TB,96.5,96.51,96.5,96.51,96.51,96.57,2200,212301,08-JAN-2024,3,IN002023Y417\n";
//
//        csvInputStream = new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8));
//    }

    @Test
    public void testUpdateStocks() throws IOException, CsvException {
//        updateStocksService.updateStocks(CsvParser.parseCsv(csvInputStream));

        assertEquals(2549, updateStocksRepository.count());
    }
}
