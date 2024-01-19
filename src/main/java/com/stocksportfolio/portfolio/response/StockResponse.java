package com.stocksportfolio.portfolio.response;

public class StockResponse {

    private Long stockId;
    private String symbol;
    private Double open;
    private Double close;
    private Double high;
    private Double low;


    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public void setPrices(Double open, Double close, Double high, Double low) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }
}
