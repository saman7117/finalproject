package com.example.demo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class StockQueue implements Comparable<StockQueue>, Serializable {
    String type;
    boolean SB; // true : buy , false : sell
    int value;
    int price;

    @Override
    public int compareTo(StockQueue s2) {
        if(this.price > s2.price)
            return 1;
        else if(this.price < s2.price)
            return -1;
        else
            return 0;
    }
}
