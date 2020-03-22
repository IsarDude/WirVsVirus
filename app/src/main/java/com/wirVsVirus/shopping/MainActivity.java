package com.wirVsVirus.shopping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    TextView toLogin;
    Button searchStores;
    EditText plz;
    private ListView mylistView;
    Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate: Started");
        setContentView(R.layout.activity_main);
        toLogin = findViewById(R.id.toLogin);
        searchStores = findViewById(R.id.search);
        plz = findViewById(R.id.plz);
        mylistView  = findViewById(R.id.listView);
        myContext=this;
    }

    public void jumpToLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    private ArrayList<Store> list;
    public void searchForStores(View view){

        try{
            Thread thread = new Thread(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    try  {
                        int postleitzahl = Integer.parseInt(plz.getText().toString());

                        Client client = new Client();
                        list = (ArrayList<Store>)client.requestStores(postleitzahl);
                        System.out.println(list.size());
                        //throws NPE wenn keine Läden bei PLZ -> Fehlermeldung / evtl. Suche nach anderen PLz


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
            thread.join();
            StoreListAdapter adapter = new StoreListAdapter( myContext, R.layout.activity_list_layout, list);
            mylistView.setAdapter(adapter);
        } catch(NumberFormatException | InterruptedException nfe){
            System.out.println("fail");
        }



    }

}