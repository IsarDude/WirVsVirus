package com.wirVsVirus.shopping;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client {
    public static String IP = "10.0.2.2";
    public static int PORT = 25731;
    private Socket sock;


    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.requestStores(81669).get(0).getPlz());
    }

    public Client() {
        try {
            sock = new Socket(IP, PORT);
        } catch (IOException e) {
            System.out.println("fail io socket");
        }
    }

    public List<Store> requestStores(int plz) {

        try {

            PrintWriter socketOut = new PrintWriter(sock.getOutputStream(), true);

            socketOut.println(plz);

            InputStream inputStream = sock.getInputStream();


            ObjectInputStream objectOutputStream = new ObjectInputStream(inputStream);


            return (List<Store>) objectOutputStream.readObject();

        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public void closeCommunication() {
        try {
            sock.close();
        } catch (IOException e) {

        }
    }
}

