/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;



import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client3 {
    private static int id = 0;
    Socket mSocket;
    int port = 9090;
    String serverAddress = "127.0.0.1";

    InputStream fromServerStream;
    OutputStream toServerStream;

    DataInputStream reader;
    PrintWriter writer;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public Client3() {
        try {

            mSocket = new Socket(serverAddress, port);

            System.out.println("connect to server ....");

            // input stream (stream from server)
            fromServerStream = mSocket.getInputStream();

            // output stream (stream to server)
            toServerStream = mSocket.getOutputStream();

            reader = new DataInputStream(fromServerStream);
            writer = new PrintWriter(toServerStream, true);
            objectInputStream = new ObjectInputStream(mSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(mSocket.getOutputStream());


            // first : read server message
//            String msg = reader.readLine();
//            System.out.println("Server :" + msg);

            menu();

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void menu() throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);

        String name= "user";

        sendName(name);

        HelloApplication.go();
    }
    private void sendName(String name){
        writer.println(name);
    }
    private String sendsignin() throws IOException {
        writer.println("signIN");
        return reader.readLine();
    }
    public static void main(String[] args) {
        new Client3();
    }
}