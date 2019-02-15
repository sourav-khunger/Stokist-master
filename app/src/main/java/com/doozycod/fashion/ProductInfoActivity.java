package com.doozycod.fashion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

//      Initialising
    Button store_one, store_two, store_three;

    ImageView iv_product_img, add_to_fav, back_pressed, show_history, show_fav, scan_now, goto_home;

    TextView product_name, product_price_one, product_price_two, product_price_three;
    boolean isPressed = true;
    int position;

    String barcode, name, product_img, store_url_1, store_url_2, store_url_3, product_price1, product_price2, product_price3;

//      Fetching Data Through DBHelper Constructor
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(ProductInfoActivity.this);

//      Getting the List value in Pojo(Model)Class to set and get Data
        List<DatabaseModel> addToFavModels = new ArrayList<>();

        setContentView(R.layout.activity_product_details);

//      Type-Casting of Buttons
        store_one = findViewById(R.id.goto_store1);
        store_two = findViewById(R.id.goto_store2);
        show_fav = findViewById(R.id.fav_icon_btn_sn);
        scan_now = findViewById(R.id.scan_now_btn);
        goto_home = findViewById(R.id.home_btn);
        store_three = findViewById(R.id.goto_store3);
        product_name = findViewById(R.id.tv_product_name);
        product_price_one = findViewById(R.id.product_price);
        product_price_two = findViewById(R.id.product_price2);
        product_price_three = findViewById(R.id.product_price3);
        add_to_fav = findViewById(R.id.add_to_fav);
        iv_product_img = findViewById(R.id.iv_product_img);
        back_pressed = findViewById(R.id.back_arrow);
        show_history = findViewById(R.id.history_icon_btn_sn);

//      Creating Bundle object to get the Parsable data
        final Bundle bundle = getIntent().getExtras();

//      Adding data to retreive from another Activity using Object of Bundle
        barcode = bundle.getString("barcode");
        name = bundle.getString("product_name");
        product_img = bundle.getString("product_img");
        store_url_1 = bundle.getString("url_1");
        store_url_2 = bundle.getString("url_2");
        store_url_3 = bundle.getString("url_3");
        product_price1 = bundle.getString("price_one");
        product_price2 = bundle.getString("price_two");
        product_price3 = bundle.getString("price_two");

//        Glide for the fetching image type from product_img
        Glide.with(this).load(product_img).into(iv_product_img);


//        Setting Values
        product_name.setText(name);
        product_price_one.setText(product_price1);
        product_price_two.setText(product_price2);
        product_price_three.setText(product_price3);


//        get status value of position from bundle
        position = bundle.getInt("position");

        if (addToFavModels.size() > 0) {

            String barcode = addToFavModels.get(position).getBarcode();

//         Getting values and adding to string from Model Class
            String name = addToFavModels.get(position).getProduct_name();
            String product_img = addToFavModels.get(position).getProduct_img();
            product_price1 = addToFavModels.get(position).getPrice_one();
            product_price2 = addToFavModels.get(position).getPrice_two();
            product_price3 = addToFavModels.get(position).getPrice_three();
            store_url_1 = addToFavModels.get(position).getStore_url_1();
            store_url_2 = addToFavModels.get(position).getStore_url_2();
            store_url_3 = addToFavModels.get(position).getStore_url_3();

//          Setting String to TextVIew
            product_name.setText(name);
            product_price_one.setText(product_price1);
            product_price_two.setText(product_price2);
            product_price_three.setText(product_price3);
            store_one.setText(store_url_1);
            store_two.setText(store_url_2);
            store_three.setText(store_url_3);

//        Glide for the fetching image type from product_img
            Glide.with(getApplicationContext()).load(product_img).into(iv_product_img);

        }

//        Check if anything Exists in fav_product Table
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        creating cursor object to use query to get the data from table
        Cursor cursor = null;
//        String for query
        String sql = "SELECT * FROM fav_products WHERE barcode_number=" + barcode;

        cursor = sqLiteDatabase.rawQuery(sql, null);

//        Condition for image Red for adding to fav_table and Grey to remove
        if (cursor.getCount() > 0) {
            add_to_fav.setImageResource(R.drawable.fav_icon_red);
            isPressed = false;
        } else {

            isPressed = true;
            add_to_fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        cursor.close();

        //Add to Fav--------------------------------------------------------------

        add_to_fav.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (isPressed) {
                    add_to_fav.setImageResource(R.drawable.fav_icon_red);

                    dbHelper.addFavorites(barcode, name, product_img, store_url_1, store_url_2, store_url_3, product_price1, product_price2, product_price3);
                    Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_LONG).show();
                    isPressed = false;
                } else {
                    isPressed = true;

                    add_to_fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    dbHelper.deleteEntry(name);

                    Toast.makeText(getApplicationContext(), "removed from favorites", Toast.LENGTH_LONG).show();
                }


            }
        });


        store_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (store_url_1 != null) {

                    Uri uri = Uri.parse(store_url_1); // missing 'http://' will cause crashed

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                } else {

                    Toast.makeText(ProductInfoActivity.this, "null ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        store_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (store_url_2 != null) {

                    Uri uri = Uri.parse(store_url_2); // missing 'http://' will cause crashed

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                } else {

                    Toast.makeText(ProductInfoActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        store_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (store_url_3 != null) {

                    Uri uri = Uri.parse(store_url_3); // missing 'http://' will cause crashed

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);


                } else {

                    Toast.makeText(ProductInfoActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });


        show_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                Bundle bundle1 = getIntent().getExtras();

                barcode = bundle1.getString("barcode");
                name = bundle1.getString("product_name");
                product_img = bundle1.getString("product_name");

                String query = "select * from " + "history_products";

                Cursor cur = db.rawQuery(query, null);

                if (cur.moveToFirst()) {
                    do {

                        barcode = cur.getString(cur.getColumnIndex("barcode_number"));
                        name = cur.getString(cur.getColumnIndex("item_name"));
                        product_img = cur.getString(cur.getColumnIndex("item_image"));
                        store_url_1 = cur.getString(cur.getColumnIndex("item_url_1"));
                        store_url_2 = cur.getString(cur.getColumnIndex("item_url_2"));
                        store_url_3 = cur.getString(cur.getColumnIndex("item_url_3"));
                        product_price1 = cur.getString(cur.getColumnIndex("item_price_1"));
                        product_price2 = cur.getString(cur.getColumnIndex("item_price_2"));
                        product_price3 = cur.getString(cur.getColumnIndex("item_price_3"));

                    } while (cur.moveToNext());
                }

                startActivity(new Intent(ProductInfoActivity.this, HistoryActivity.class));

            }
        });

        //        show Favortites
        show_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                Bundle bundle1 = getIntent().getExtras();

                barcode = bundle1.getString("barcode");
                name = bundle1.getString("product_name");
                product_img = bundle1.getString("product_name");
                product_price1 = bundle1.getString("product_price");

                String query = "select * from " + "fav_products";

                Cursor cur = db.rawQuery(query, null);

                if (cur.moveToFirst()) {
                    do {

                        barcode = cur.getString(cur.getColumnIndex("barcode_number"));
                        name = cur.getString(cur.getColumnIndex("item_name"));
                        product_img = cur.getString(cur.getColumnIndex("item_image"));
                        store_url_1 = cur.getString(cur.getColumnIndex("item_url_1"));
                        store_url_2 = cur.getString(cur.getColumnIndex("item_url_2"));
                        store_url_3 = cur.getString(cur.getColumnIndex("item_url_3"));
                        product_price1 = cur.getString(cur.getColumnIndex("item_price_1"));
                        product_price2 = cur.getString(cur.getColumnIndex("item_price_2"));
                        product_price3 = cur.getString(cur.getColumnIndex("item_price_3"));

                    } while (cur.moveToNext());
                }

                startActivity(new Intent(ProductInfoActivity.this, AddToFavActivity.class));
            }
        });


//        Show Discover OR Home Page
        goto_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductInfoActivity.this, Discover.class));
            }
        });
        scan_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductInfoActivity.this, BarCodeScanActivity.class));
            }
        });
        // OnBackPressed Listener
        back_pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                startActivity(new Intent(getApplicationContext(), Discover.class));
                finish();
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
