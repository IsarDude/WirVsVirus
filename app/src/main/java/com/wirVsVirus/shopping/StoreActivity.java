package com.wirVsVirus.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StoreActivity extends AppCompatActivity {

    TextView storeName,storeTime,storeAddress,textViewNow,textViewLater;
    Button colorViewNow,colorViewLater;
    EditText date,time;
    Button plan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        storeTime = findViewById(R.id.storeTimes);
        textViewLater = findViewById(R.id.textViewLater);
        textViewNow = findViewById(R.id.textViewNow);
        colorViewLater = findViewById(R.id.colorViewLater);
        colorViewNow = findViewById(R.id.colorViewNow);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        plan = findViewById(R.id.plan);

        Store thisStore = (Store) getIntent().getSerializableExtra("StoreObj");
        storeName.setText(thisStore.getMarkttyp());
        storeAddress.setText("Addresse: \n" + thisStore.getStrasse() + " " +  thisStore.getHausnr() + "\n" + thisStore.getPlz() + " " + thisStore.getOrt());
        int i = thisStore.getActivity();
        switch (i){
            case -1:
                colorViewNow.setBackgroundColor(Color.GRAY);
                textViewNow.setText("unbekannt");
                break;
            case 0:
                colorViewNow.setBackgroundColor(Color.GREEN);
                textViewNow.setText("gering");
                break;
            case 1:
                colorViewNow.setBackgroundColor(Color.YELLOW);
                textViewNow.setText("mittel");
                break;
            case 2:
                colorViewNow.setBackgroundColor(Color.RED);
                textViewNow.setText("hoch");
                break;
        }


    }

    public void toPlan(View view){
        String datee = date.getText().toString();
        String timee = time.getText().toString();
        if(timee.isEmpty() || datee.isEmpty()){
            Toast.makeText(this, "Bitte beide Felder ausfüllen", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Ihre Zeit wurd eingeplant", Toast.LENGTH_SHORT).show();
        }
    }


}
