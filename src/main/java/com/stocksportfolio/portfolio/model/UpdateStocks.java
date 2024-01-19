package com.stocksportfolio.portfolio.model;

import jakarta.persistence.*;

@Entity
public class UpdateStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String series;

    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double last;

    @Column(name = "prev_close")
    private Double prevClose;

    @Column(name = "total_traded_quantity")
    private Long totalTradedQuantity;

    @Column(name = "total_traded_value")
    private Double totalTradedValue;

    private String timestamp;

    @Column(name = "total_trades")
    private Long totalTrades;

    @Column(nullable = false)
    private String isin;

    public UpdateStocks() {
    }

    public UpdateStocks(String symbol, String series, Double open, Double high, Double low,
                     Double close, Double last, Double prevClose, Long totalTradedQuantity,
                     Double totalTradedValue, String timestamp, Long totalTrades, String isin) {
        this.symbol = symbol;
        this.series = series;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.last = last;
        this.prevClose = prevClose;
        this.totalTradedQuantity = totalTradedQuantity;
        this.totalTradedValue = totalTradedValue;
        this.timestamp = timestamp;
        this.totalTrades = totalTrades;
        this.isin = isin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
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

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(Double prevClose) {
        this.prevClose = prevClose;
    }

    public Long getTotalTradedQuantity() {
        return totalTradedQuantity;
    }

    public void setTotalTradedQuantity(Long totalTradedQuantity) {
        this.totalTradedQuantity = totalTradedQuantity;
    }

    public Double getTotalTradedValue() {
        return totalTradedValue;
    }

    public void setTotalTradedValue(Double totalTradedValue) {
        this.totalTradedValue = totalTradedValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTotalTrades() {
        return totalTrades;
    }

    public void setTotalTrades(Long totalTrades) {
        this.totalTrades = totalTrades;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "Stocks {" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", series='" + series + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", prevClose=" + prevClose +
                ", totalTradedQuantity=" + totalTradedQuantity +
                ", totalTradedValue=" + totalTradedValue +
                ", timestamp='" + timestamp + '\'' +
                ", totalTrades=" + totalTrades +
                ", isin='" + isin + '\'' +
                '}';
    }
}
