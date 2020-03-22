package com.wirVsVirus.shopping;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



public class User {

    private Store store;
    private String email;
    private String name;
    private String phone;
    private Socket sock;

    private String IP = "12";
    private int PORT = 132;

    public User() {

    }

    public boolean claimStore(Integer plz, String str) {

        if(!store.isClaimed()) {
            store.setOwner(this);
        }

        return false;
    }

    public void setActivity(int status) {
        if(store!=null) {
            try {
                sock = new Socket(IP, PORT);
                PrintWriter socketOut = new PrintWriter(sock.getOutputStream(), true);
                socketOut.println(store.getIndex());
                socketOut.println(status);
                socketOut.close();
                DataInputStream serverAnswer = new DataInputStream(sock.getInputStream());
                int answer = (int) serverAnswer.read();
                if (answer == 1) {
                    store.setActivity(status);
                } else {
                    System.err.println("Server error!");
                    throw new IOException();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }



}

