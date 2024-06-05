package com.example.demo;

import javafx.beans.value.ObservableValue;

public class Stock {
    private String market;
    private double price;
    private double changes;
    private double maxPrice;
    private double minPrice;

    public Stock(String market1, double v, double v1, double v2, double v3) {
        market = market1;
        price = v;
        changes = v1;
        maxPrice = v2;
        minPrice= v3;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChanges() {
        return changes;
    }

    public void setChanges(double changes) {
        this.changes = changes;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
