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



        }
        catch (Exception e) {
        }
    }
}