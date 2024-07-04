/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Server5 {
    ServerSocket mServer;
    int serverPort = 9090;
    ArrayList<Thread> threads = new ArrayList<Thread>();
    int limit = 100;
    HashMap<String, ClientManager> clientsMap=new HashMap<String, ClientManager>();

    public Server5() throws SQLException {
        try {
            // create server socket!
            mServer = new ServerSocket(serverPort);

            System.out.println("Server Created!");

            // always running
            while (true) {
                // wait for client
                // hold an object of Socket for each client
                Socket client = mServer.accept();

                System.out.println("Connected to New Client!");

                // create new thread to manage each client separately and
                // simultaneously
                Thread t = new Thread(new ClientManager(this,client));

                // add Thread to "threads" list
                threads.add(t);

                if (threads.size() > limit) {
                    // run your scheduling algorithm
                }
                // start thread
                t.start();

            }
        } catch (IOException e) {
        }

    }

    public void addClientManager(String clientName, ClientManager cm){
        clientsMap.put(clientName, cm);
    }

    public static void main(String[] args) throws SQLException {
        new Server5();
    }

}