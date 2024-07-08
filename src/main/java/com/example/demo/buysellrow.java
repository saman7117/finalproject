package com.example.demo;

public class buysellrow {
    private String buyer;
    private double price;
    private double amount;
    private String seller;

    public buysellrow(String seller, double price, double amount, String buyer) {
        this.seller = seller;
        this.price = price;
        this.amount = amount;
        this.buyer = buyer;
    }
    public buysellrow(){

    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
