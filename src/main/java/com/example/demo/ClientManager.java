/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

/**
 *
 * @author Ramin
 */

import com.example.demo.HelloApplication;
import com.example.demo.Server5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ClientManager implements Runnable {
    private static ArrayList <StockQueue> test = new ArrayList<>();
    private static int id = 0;
    Socket clientHolder;
    Server5 serverHolder;
    InputStream fromClientStream;
    OutputStream toClientStream;
    DataInputStream reader;
    PrintWriter writer;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ClientManager(Server5 server,Socket client) {
        serverHolder = server;
        clientHolder = client;
    }

    public void run() {
        try {
            // input stream (stream from client)
            fromClientStream = clientHolder.getInputStream();

            // output stream (stream to client)
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);
            objectOutputStream = new ObjectOutputStream(clientHolder.getOutputStream());



            // send message to client
//			writer.println("What is your name?");
//			System.out.println("Server :What is your name?");

            // Receive client response (javab=name:D)
            String name = reader.readLine();
            System.out.println("HI "+name+id);
            name+=id;
            id++;

            //add "this" to Server "clientsMap" HashMap
            serverHolder.addClientManager(name,this);

            ArrayList <StockQueue> BUY = new ArrayList<>();
            ArrayList <StockQueue> SELL = new ArrayList<>();
            while (true) {
                // read command from client
                String command = reader.readLine();
                // now decide by command ;-)
                if (command.equals("e")) {
                    String string = reader.readLine();
                    String [] strings = new String[4];
                    if (string.equals("f")){
                        for (int i = 0; i < 4; i++) {
                            String r = reader.readLine();
                            strings[i] = r;
                        }
                    }
                    StockQueue stockQueue = new StockQueue();
                    stockQueue.type = strings[0];
                    stockQueue.value = Integer.parseInt(strings[2]);
                    stockQueue.price = Integer.parseInt(strings[3]);
                    if (strings[1].equals("sell"))
                        stockQueue.SB = false;
                    else
                        stockQueue.SB = true;
                    test.add(stockQueue);
                }
                else if(command.equals("s")){
                    BUY.clear();
                    SELL.clear();
                    String type = reader.readLine();
                    for (int i = 0; i < test.size(); i++) {
                        if (test.get(i).type.equals(type)){
                            if (test.get(i).SB)
                                BUY.add(test.get(i));
                            else
                                SELL.add(test.get(i));
                        }
                    }
                    if (BUY.size() > 0) {
                        writer.println("buy");
                        objectOutputStream.writeObject(BUY);
                    }
                    else
                        writer.println("nobuy");
                    if (SELL.size() > 0) {
                        writer.println("sell");
                        objectOutputStream.writeObject(SELL);
                    }
                    else
                        writer.println("nosell");
                    System.out.println("###################################################");
                }
            }
        }
        catch (Exception e) {
        }
    }
}