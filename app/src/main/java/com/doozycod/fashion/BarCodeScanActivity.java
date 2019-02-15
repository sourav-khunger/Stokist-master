package com.doozycod.fashion;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

// BarCodeScanActivity to scan the barcode with the Implemention of ZxingScanner
public class BarCodeScanActivity extends Activity implements ZXingScannerView.ResultHandler {
    //    Initialising
    private ZXingScannerView mScannerView;
    DBHelper dbHelper;
    String barcode, product_name, product_img, url_1, url_2, url_3, price_one, price_two, price_three;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

//      Fetching Data Through DBHelper Constructor
        dbHelper = new DBHelper(BarCodeScanActivity.this);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setAutoFocus(true);

//      Retriving Data Through DBHelper method
        dbHelper.insertContact_one();
        dbHelper.insertContact_two();
        dbHelper.insertContact_three();
    }

    //      Predefined Method onResume
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    //      Predefined Method onPause
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    //      Predefined method of ZxingScanner to Receive the Result of Barcode
    @Override
    public void handleResult(Result rawResult) {

//      Creating object of SqliteDatabase to access the Database to write by using getWritableDatabase
        SQLiteDatabase db = dbHelper.getReadableDatabase();

//        String for query
        String query = "select * from " + "static_products" + " where " + "barcode_number" + " = '" + rawResult.toString().replaceAll("  ", "") + "'";

//      Creating cursor object to use query to get the data from table
        Cursor cur = db.rawQuery(query, null);

//        Condition for set the data
        if (cur.moveToFirst()) {
            do {
                barcode = cur.getString(cur.getColumnIndex("barcode_number"));
                product_name = cur.getString(cur.getColumnIndex("item_name"));
                product_img = cur.getString(cur.getColumnIndex("item_image"));
                url_1 = cur.getString(cur.getColumnIndex("item_url_1"));
                url_2 = cur.getString(cur.getColumnIndex("item_url_2"));
                url_3 = cur.getString(cur.getColumnIndex("item_url_3"));
                price_one = cur.getString(cur.getColumnIndex("item_price_1"));
                price_two = cur.getString(cur.getColumnIndex("item_price_2"));
                price_three = cur.getString(cur.getColumnIndex("item_price_3"));

            } while (cur.moveToNext());
        }

//          Creating Bundle object to get the Parsable data
        Bundle bundle = new Bundle();

//          Adding data to parse to another activity using Object of Bundle
        bundle.putString("barcode", barcode);
        bundle.putString("product_name", product_name);
        bundle.putString("product_img", product_img);
        bundle.putString("url_1", url_1);
        bundle.putString("url_2", url_2);
        bundle.putString("url_3", url_3);
        bundle.putString("price_one", price_one);
        bundle.putString("price_two", price_two);
        bundle.putString("price_three", price_three);

//      Adding Data into History Table using dbhelper object and it's method addHistory
        dbHelper.addHistory(barcode, product_name, product_img, url_1, url_2, url_3, price_one, price_two, price_three);

//            Creating an Intent Object to Start Activity and Parse the Data to another activity
        Intent intent_pass_productInfo = new Intent(BarCodeScanActivity.this, ProductInfoActivity.class);

//          Adding bundle object to intent Object to using putExtras to get the data in Another Activity
        intent_pass_productInfo.putExtras(bundle);
//          Starting Another Activity using StartActvity and Intent Object
        startActivity(intent_pass_productInfo);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Discover.class));
        finish();
    }


}
