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

public class AddToFavActivity extends AppCompatActivity {
    // Initialising
    RecyclerView recyclerView;

    ImageView onBackPress;
    LinearLayout home_button, scan_button, history_button;

    DBHelper dbHelper;
    List<DatabaseModel> show_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//      Fetching Data Through DBHelper Constructor
        dbHelper = new DBHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fav);

//      Type-Casting of Buttons
        onBackPress = findViewById(R.id.back_arrow_add_to);
        scan_button = findViewById(R.id.scan_button);
        home_button = findViewById(R.id.home_button);
        history_button = findViewById(R.id.history_button);

//        Getting the List value in Pojo(Model)Class to set and get Data
        show_fav = new ArrayList<>();

//        Getting Values from Database Through it getDataFromDbForFav() Method to show on Favorite Activity
        show_fav = dbHelper.getDataFromDbForFav();

//        Binding the xml recycler view with java
        recyclerView = findViewById(R.id.add_to_list_view);

//        Giving recyclerview fixed size for any kind it contains
        recyclerView.setHasFixedSize(true);

//        Setting Item Animator to Animate it by defaultItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        Setting the Layout Type Linear to Recyclerview by calling its LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Attaching the AddtoFavAdapter to AddtoFavActivity to get Recyclerview
        AddToFavAdapter recyclerAdapter = new AddToFavAdapter(this, show_fav);

//        Setting Adapter to the AddToFavActivity to get a view of Data in RecyclerView in AddtoFavActivity
        recyclerView.setAdapter(recyclerAdapter);


//        Calling onBackPressed method to go back using Header Back button
        onBackPress.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(AddToFavActivity.this, BarCodeScanActivity.class));
            }
        });

//        Calling OnClickListener to go to Discover Activity when Home is Pressed

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                starting Discover Activity
                startActivity(new Intent(AddToFavActivity.this, Discover.class));

            }
        });

//        Calling OnClickListener to go to History Activity when History Button is Pressed

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                starting the History Activity
                startActivity(new Intent(AddToFavActivity.this, HistoryActivity.class));

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
