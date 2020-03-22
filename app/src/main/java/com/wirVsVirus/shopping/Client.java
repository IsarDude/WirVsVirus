package com.wirVsVirus.shopping;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Client {
    public static String IP = "10.0.2.2";
    public static int PORT = 34350;
    Socket sock;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.requestStores(81669).get(0).getPlz());
    }

    public Client() {
        try {
            sock = new Socket(IP, PORT);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Store> requestStores(int plz) {

        try {

            PrintWriter socketOut = new PrintWriter(sock.getOutputStream(), true);

            socketOut.println(plz);

            InputStream inputStream = sock.getInputStream();

            List<Store> list = new ArrayList<Store>();

            Consumer<String> mapper = x -> {
                Stream.of(x).map(next -> next.split("\\|")).forEach(str -> {

                    try {
                        list.add(new Store(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8],
                                str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18],
                                str[19], str[20], str[21], str[22], str[23], str[24], str[25],
                                str[26].replace(',', '.'), str[27].replace(',', '.')));

                    } catch (NumberFormatException ignored) {
                        System.out.println(ignored.toString());
                    }
                });
            };
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedReader.lines().forEach(mapper);
            bufferedReader.close();

            return list;

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public void closeCommunication() {
        try {
            sock.close();
        } catch (IOException e) {

        } catch (NullPointerException e) {

        }
    }
}
