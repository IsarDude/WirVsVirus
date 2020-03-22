package com.wirVsVirus.shopping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView toLogin;
    Button searchStores;
    EditText plz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toLogin = findViewById(R.id.toLogin);
        searchStores = findViewById(R.id.search);
        plz = findViewById(R.id.postleitzahl);
    }

    public void jumpToLogin(View view){


    }

    public void searchForStores(View view){
        try{
            Thread thread = new Thread(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    try  {
                        int postleitzahl = Integer.parseInt(plz.getText().toString());

                        Client client = new Client();
                        List<Store> list = client.requestStores(postleitzahl);
                        System.out.println(list.size());
                        //throws NPE wenn keine Läden bei PLZ -> Fehlermeldung / evtl. Suche nach anderen PLz
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        } catch(NumberFormatException nfe){
            System.out.println("fail");
        }



    }

}