package com.wirVsVirus.shopping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

   private static final String TAG = "MainActivity" ;
    TextView toLogin;
    Button searchStores;
    EditText plz;
    private ListView mylistView;
    Context myContext;
    FirebaseDatabase database;
    public static Path path = Paths.get("C:\\Users\\David\\Desktop\\databaseHackaton.csv");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toLogin = findViewById(R.id.toLogin);
        searchStores = findViewById(R.id.search);
        plz = findViewById(R.id.plz);
        mylistView = findViewById(R.id.listView);
        myContext = this;
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Store");
        boolean running = true;
        LinkedList<Store> list = new LinkedList<Store>();



    }

        public void jumpToLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    private ArrayList<Store> list;

    public void searchForStores(View view){

        try{
            Thread thread = new Thread(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.O)
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
            mylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent storeIntent = new Intent(getApplicationContext(),StoreActivity.class);
                    storeIntent.putExtra("storeObj",list.get(position));
                    startActivity(storeIntent);
                }
            });
        } catch(NumberFormatException | InterruptedException nfe){
            System.out.println("fail");
        }



    }

}