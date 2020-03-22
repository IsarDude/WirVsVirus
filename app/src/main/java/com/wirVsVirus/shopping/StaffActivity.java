package com.wirVsVirus.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



public class StaffActivity extends AppCompatActivity {
private String userId;
private int currentActivity;
private SeekBar status;
Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        //socket = new Socket(IP, PORT);
        try {
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            socketOut.println(1);
            socketOut.println(userId);
            socketOut.close();
            DataInputStream socketIn = new DataInputStream(socket.getInputStream());
            currentActivity = socketIn.read();
            socketIn.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

