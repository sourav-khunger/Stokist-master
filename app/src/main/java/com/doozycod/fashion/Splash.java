package com.doozycod.fashion;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// Splash Screen Activity
public class Splash extends AppCompatActivity {
    int DELAYE_TIME = 3000;
    DBHelper product_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        product_DB = new DBHelper(Splash.this);

//        Handler for creating a delay to start the Another Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Discover.class));
                finish();

            }
        }, DELAYE_TIME);

    }
}

