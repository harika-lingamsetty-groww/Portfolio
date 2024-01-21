
package com.stocksportfolio.portfolio.response;

import com.stocksportfolio.portfolio.model.Portfolio;

import java.util.List;

public class PortfolioResponse {

    private List<Holding> holdings;
    private Double totalPortfolioHolding;
    private Double totalBuyPrice;
    private String totalPLPercentage;


    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public Double getTotalPortfolioHolding() {
        return totalPortfolioHolding;
    }

    public void setTotalPortfolioHolding(Double totalPortfolioHolding) {
        this.totalPortfolioHolding = totalPortfolioHolding;
    }

    public Double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(Double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }


    public String getTotalPLPercentage() {
        return totalPLPercentage;
    }

    public void setTotalPLPercentage(String totalPLPercentage) {
        this.totalPLPercentage = totalPLPercentage;
    }

    public void setTotalPLandPLPercentage(double v, double v1) {
        if (v == 0 && v1 == 0) {
            this.totalPLPercentage = "0.00%";
        } else {
            double percentage = Math.abs(((v - v1) / v1) * 100);

            if (v > v1) {
                this.totalPLPercentage = String.format("%.2f%% Profit", percentage);
            } else {
                this.totalPLPercentage = String.format("%.2f%% Loss", percentage);
            }
        }
    }

    public static class Holding {
        private Long stockId;
        private String stockName;
        private int quantity;
        private Double buyPrice;
        private Double currentPrice;
        private Double gainLoss;
        private String gainLossPercentage;


        public Long getStockId() {
            return stockId;
        }

        public void setStockId(Long stockId) {
            this.stockId = stockId;
        }

        public String getStockName() {
            return stockName;
        }

        public void setSymbol(String stockName) {
            this.stockName = stockName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(Double buyPrice) {
            this.buyPrice = buyPrice;
        }

        public Double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public Double getGainLoss() {
            return gainLoss;
        }

        public void setGainLoss(Double gainLoss) {
            this.gainLoss = gainLoss;
        }

        public String getGainLossPercentage() {
            return gainLossPercentage;
        }

        public void setGainLossPercentage(String gainLossPercentage) {
            this.gainLossPercentage = gainLossPercentage;
        }
    }
}
