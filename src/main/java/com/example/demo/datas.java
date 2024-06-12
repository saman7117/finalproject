package com.example.demo;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class datas {
    public static String id;
    public static String username;
    public static String firstName;
    public static String lastName;
    public static String phoneNumber;
    public static String Gmail;
    public static String role;
    public static String Password;
    public static double YEN;
    public static double USD;
    public static double EUR;
    public static double TMN;
    public static double GBT;
    public static double total;
    public static int[] time;
    public static double USDPrice;
    public static double TMNPrice;
    public static double GBPPrice;
    public static double EURPrice;
    public static double YENPrice;
    public static double[] MINPrice = new double[5];//0-USD / 1-TMN / 2-YEN / 3-GBP / 4-EUR
    public static double[] MAXPrice = new double[5];//0-USD / 1-TMN / 2-YEN / 3-GBP / 4-EUR
    public static DataInputStream MainReader;
    public static PrintWriter MainWriter;
    public static ObjectInputStream objectMainReader;
    public static ObjectOutputStream objectOutputStream;


}
