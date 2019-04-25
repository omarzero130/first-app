package com.example.omarshalaby.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        t.start();


    }
    Thread t=new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(4000);

            } catch (Exception e) {
                Log.i("text", "run: exception ");
            }

            Intent i = new Intent(splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }



    });
}
