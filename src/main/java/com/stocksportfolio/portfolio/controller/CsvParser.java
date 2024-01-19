package com.stocksportfolio.portfolio.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.stocksportfolio.portfolio.model.UpdateStocks;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    private static final String[] CSV_HEADER = { "SYMBOL", "SERIES", "OPEN", "HIGH", "LOW", "CLOSE", "LAST",
            "PREVCLOSE", "TOTTRDQTY", "TOTTRDVAL", "TIMESTAMP", "TOTALTRADES", "ISIN" };

    public static List<UpdateStocks> parseCsv(InputStream inputStream) throws IOException, CsvException {
        InputStreamReader reader = new InputStreamReader(inputStream);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> csvData = csvReader.readAll();

        List<UpdateStocks> stocks = new ArrayList<>();

        for (String[] row : csvData) {
            if (row.length != CSV_HEADER.length) {
                throw new CsvException("Invalid CSV format");
            }
            UpdateStocks stock = getUpdateStocks(row);
            stocks.add(stock);
        }

        return stocks;
    }

    private static UpdateStocks getUpdateStocks(String[] row) {
        UpdateStocks stock = new UpdateStocks();
        stock.setSymbol(row[0]);
        stock.setSeries(row[1]);
        stock.setOpen(Double.parseDouble(row[2]));
        stock.setHigh(Double.parseDouble(row[3]));
        stock.setLow(Double.parseDouble(row[4]));
        stock.setClose(Double.parseDouble(row[5]));
        stock.setLast(Double.parseDouble(row[6]));
        stock.setPrevClose(Double.parseDouble(row[7]));
        stock.setTotalTradedQuantity(Long.parseLong(row[8]));
        stock.setTotalTradedValue(Double.parseDouble(row[9]));
        stock.setTimestamp(row[10]);
        stock.setTotalTrades(Long.parseLong(row[11]));
        stock.setIsin(row[12]);
        return stock;
    }
}
