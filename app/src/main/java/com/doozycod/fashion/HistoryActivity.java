package com.doozycod.fashion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<DatabaseModel> dbList;
    ImageView back_btn, scan_now, home_btn, fav_btn;
    LinearLayout home_button,scan_button,favorite_button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Type-Casting
        back_btn = findViewById(R.id.back_arrow_recycler);
        scan_button = findViewById(R.id.scan_button);
        home_button = findViewById(R.id.home_button);
        favorite_button = findViewById(R.id.fav_button);
        recyclerView = findViewById(R.id.recycler_list_view);

        // Fetching Data Through DBHelper Constructor
        dbHelper = new DBHelper(this);

        // Getting the List value in Pojo(Model)Class to set and get Data
        dbList = new ArrayList<>();

        //  Getting Values from Database Through it getDataFromDbForHistory() Method to show on Favorite Activity
        dbList = dbHelper.getDataFromDbForHistory();

        //        Giving recyclerview fixed size for any kind it contains
        recyclerView.setHasFixedSize(true);
        //        Setting Item Animator to Animate it by defaultItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //        Setting the Layout Type Linear to Recyclerview by calling its LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //        Attaching the RecyclerAdapter to HistoryActivity to get Recyclerview
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, dbList);

        //        Setting Adapter to the AddToFavActivity to get a view of Data in RecyclerView in AddtoFavActivity
        recyclerView.setAdapter(recyclerAdapter);

        //        Calling onBackPressed method to go back using Header Back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                startActivity(new Intent(getApplicationContext(), Discover.class));
                finish();
            }
        });

        //        Calling OnClickListener to do Action when Scan Button is Pressed
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //        Start Another Activity using startActivity and Intent
                startActivity(new Intent(HistoryActivity.this, BarCodeScanActivity.class));
            }
        });

        //        Calling OnClickListener to go to Discover Activity when Home is Pressed
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        starting Discover Activity
                startActivity(new Intent(HistoryActivity.this, Discover.class));

            }
        });
//        Calling OnClickListener to go to AddtoFavActivity when Favorite Button is Pressed
        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, AddToFavActivity.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Discover.class));
        finish();
    }
}
