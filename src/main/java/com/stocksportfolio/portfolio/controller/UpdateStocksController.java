package com.stocksportfolio.portfolio.controller;

import com.opencsv.exceptions.CsvException;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import com.stocksportfolio.portfolio.service.UpdateStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/update-stocks")
public class UpdateStocksController {

    @Autowired
    private UpdateStocksService updateStocksService;

    @PostMapping
    public ResponseEntity<String> updateStocks(@RequestParam("file") MultipartFile file) {
        try {
            List<UpdateStocks> stocks = CsvParser.parseCsv(file.getInputStream());
            updateStocksService.updateStocks(stocks);
            return ResponseEntity.ok("Stocks updated successfully");
        } catch (IOException | CsvException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing CSV file: " + e.getMessage());
        }
    }
}
