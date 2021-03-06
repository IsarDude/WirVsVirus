package com.wirVsVirus.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    ProgressBar splashProgress;
    private int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //splashProgress = findViewById(R.id.splashProgress);
        //playProgress();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, SPLASH_TIME);

    }
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();
    }
}
