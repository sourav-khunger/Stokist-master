package com.doozycod.fashion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


// DBHelper class is SQliteHelper class which is creating Db and table to store data and show the data in the app
// and extending it with SqliteOpenHelper to get the fuctinality of Sqlite to access database
public class DBHelper extends SQLiteOpenHelper {

    //    Initialising the Variables for the data
    public static final String DATABASE_NAME = "Products.db";
    public static final String TABLE_NAME = "static_products";
    public static final String FAV_TABLE_NAME = "fav_products";
    public static final String HISTORY_TABLE_NAME = "history_products";
    public static final String COLUMN_ID = "colum_id";
    public static final String BARCODE_NUMBER = "barcode_number";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_IMAGE = "item_image";
    public static final String ITEM_URL_1 = "item_url_1";
    public static final String ITEM_URL_2 = "item_url_2";
    public static final String ITEM_URL_3 = "item_url_3";
    public static final String ITEM_price_1 = "item_price_1";
    public static final String ITEM_price_2 = "item_price_2";
    public static final String ITEM_price_3 = "item_price_3";

    //    Creating Contructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //    Auto-generated Method onCreate
    public void onCreate(SQLiteDatabase db) {

        // creating Tables in database using execSQL method

        db.execSQL("create table " + TABLE_NAME +
                "(colum_id integer primary key, barcode_number text,item_name text,item_image text, item_url_1 text,item_url_2 text, item_url_3 text,item_price_1 text,item_price_2 text,item_price_3 text)"
        );

        db.execSQL("create table " + FAV_TABLE_NAME +
                "(colum_id integer primary key, barcode_number text,item_name text,item_image text, item_url_1 text,item_url_2 text, item_url_3 text,item_price_1 text,item_price_2 text,item_price_3 text)"
        );

        db.execSQL("create table " + HISTORY_TABLE_NAME +
                "(colum_id integer primary key, barcode_number text,item_name text,item_image text, item_url_1 text,item_url_2 text, item_url_3 text,item_price_1 text,item_price_2 text,item_price_3 text)"
        );


    }

    //      If you want to some Changes(Modify in table or delete the exists table) then we do in onUpgrade method
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }

//      User Define method to add Data in Table of static_products
    public boolean insertContact_one() {

//      Creating object of SqliteDatabase to access the Database to write by using getWritableDatabase
        SQLiteDatabase db = this.getWritableDatabase();

//      Creating ContentValue Object to put value in table
        ContentValues contentValues = new ContentValues();

//      Adding Values to table
        contentValues.put("barcode_number", "1235665485");
        contentValues.put("item_name", "Apple Iphone X 64GB Space Grey");
        contentValues.put("item_image", "https://rukminim1.flixcart.com/image/832/832/j9d3bm80/mobile/k/x/a/apple-iphone-x-mqa82hn-a-original-imaeyysgmypxmazk.jpeg");
        contentValues.put("item_url_1", "https://www.flipkart.com/apple-iphone-x-space-gray-64-gb/p/itmexrgv6hctyrav?pid=MOBEXRGVCMGVCGGQ&srno=s_1_2&otracker=search&lid=LSTMOBEXRGVCMGVCGGQF4VOG4&fm=SEARCH&iid=bdad7a77-f4c6-4f4e-99b5-5b4458894c04.MOBEXRGVCMGVCGGQ.SEARCH&ppt=ProductPage&ppn=ProductPage&ssid=h2yz0nnrhc0000001549529624613&qH=882a0465d260983a");
        contentValues.put("item_url_2", "https://www.amazon.in/Apple-iPhone-Space-Grey-Storage/dp/B072LPF91D/ref=sr_1_1?s=electronics&ie=UTF8&qid=1549529611&sr=1-1&keywords=iphone+8+plus");
        contentValues.put("item_url_3", "https://paytmmall.com/apple-iphone-x-64-gb-grey-CMPLXMOBAPPLE-IPHONEDUMM14144826D4A-pdp?product_id=145167750&channel=WEB&discoverability=1&src=grid&svc=2&tracker=%7C%7C%7C%7C%2Fh%2Fiphone-clpid-7898-iPhone%20X%7C44879%7C2%7C%7C%7C3%7C");
        contentValues.put("item_price_1", "78,000 Rs/-");
        contentValues.put("item_price_2", "74,999 Rs/-");
        contentValues.put("item_price_3", "83,023 Rs/-");

//        Adding data into db and table using insert method
        db.insert("static_products", null, contentValues);
        return true;
    }
//          User Define method to add Data in Table of static_products

    public boolean insertContact_two() {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("barcode_number", "8743224152");
        contentValues.put("item_name", "Samsung Galaxy Note 9 6GB/64GB Ocean Blue");
        contentValues.put("item_image", "https://images-na.ssl-images-amazon.com/images/I/71KIDufVRUL._SY879_.jpg");
        contentValues.put("item_url_1", "https://www.flipkart.com/samsung-galaxy-note-9-ocean-blue-128-gb/p/itmf7x9z6fas7ghw?pid=MOBF7HXT4JNEEPV3&lid=LSTMOBF7HXT4JNEEPV3DHJVCQ&marketplace=FLIPKART&srno=s_1_2&otracker=search&fm=SEARCH&iid=5f959f02-ef1f-4195-8511-555a1660f791.MOBF7HXT4JNEEPV3.SEARCH&ppt=SearchPage&ppn=Search&ssid=ih9qnv0p9c0000001549530107106&qH=a8d85c18f3260e43");
        contentValues.put("item_url_2", "https://www.amazon.in/Samsung-Galaxy-Note-Ocean-Blue/dp/B07HBF3M1K/ref=sr_1_5?s=electronics&ie=UTF8&qid=1549529942&sr=1-5&keywords=samsung+galaxy+note+9");
        contentValues.put("item_url_3", "https://paytmmall.com/samsung-galaxy-note-9-128-gb-ocean-blue-MOBSAMSUNG-GALALONG6493996A3645CE-pdp?product_id=210694580&src=search-grid&tracker=organic%7C66781%7Cnote%209%7Cgrid%7CSearch_experimentName%3Dnew_ranking%7C%7C3%7Cnew_ranking&site_id=2&child_site_id=6");
        contentValues.put("item_price_1", "60,999 Rs/-");
        contentValues.put("item_price_2", "59,700 Rs/-");
        contentValues.put("item_price_3", "67,900 Rs/-");

        db.insert("static_products", null, contentValues);
        return true;
    }

//          User Define method to add Data in Table of static_products

    public boolean insertContact_three() {

//      Creating object of SqliteDatabase to access the Database to write by using getWritableDatabase
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("barcode_number", "8009220052");
        contentValues.put("item_name", "Samsung Galaxy S9 MidNight Black 4GB/64GB");
        contentValues.put("item_image", "https://images-na.ssl-images-amazon.com/images/I/61fkN2-gxwL._SY879_.jpg");
        contentValues.put("item_url_1", "https://www.flipkart.com/samsung-galaxy-s9-midnight-black-64-gb/p/itmf33a69rpszgzn?pid=MOBF2VWVBGCT5QQN&srno=s_1_1&otracker=search&lid=LSTMOBF2VWVBGCT5QQN0ZJFUP&fm=SEARCH&iid=54b76506-1385-428d-a1a1-7e636994c67d.MOBF2VWVBGCT5QQN.SEARCH&ppt=SearchPage&ppn=Search&ssid=7r5w0d5xk00000001549530376788&qH=03e04301b15a7969");
        contentValues.put("item_url_2", "https://www.amazon.in/Samsung-Galaxy-Midnight-Storage-Offers/dp/B07CSDCJG8/ref=sr_1_1?s=electronics&ie=UTF8&qid=1549530219&sr=1-1&keywords=s9");
        contentValues.put("item_url_3", "https://paytmmall.com/samsung-galaxy-s9-128-gb-midnight-black-MOBSAMSUNG-GALALONG649399FA711FE3-pdp?product_id=210822369&src=search-grid&tracker=organic%7C66781%2C78344%7Cs9%7Cgrid%7CSearch_experimentName%3Dnew_ranking%7C%7C1%7Cnew_ranking&site_id=2&child_site_id=6");
        contentValues.put("item_price_1", "57,900 Rs/-");
        contentValues.put("item_price_2", "48,900 Rs/-");
        contentValues.put("item_price_3", "61,900 Rs/-");
        db.insert("static_products", null, contentValues);
        return true;
    }

    //          User Define method to add Data in Table of fav_products using parameters

    public boolean addFavorites(String barcode, String item_name, String item_image, String item_url_1, String item_url_2, String item_url_3, String item_price_1, String item_price_2, String item_price_3) {

//      Creating object of SqliteDatabase to access the Database to write by using getWritableDatabase
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("barcode_number", barcode);
        contentValues.put("item_name", item_name);
        contentValues.put("item_image", item_image);
        contentValues.put("item_url_1", item_url_1);
        contentValues.put("item_url_2", item_url_2);
        contentValues.put("item_url_3", item_url_3);
        contentValues.put("item_price_1", item_price_1);
        contentValues.put("item_price_2", item_price_2);
        contentValues.put("item_price_3", item_price_3);
        db.insert(FAV_TABLE_NAME, null, contentValues);

        return true;
    }
    //          User Define method to add Data in Table of history_product using parameters


    public boolean addHistory(String barcode, String item_name, String item_image, String item_url_1, String item_url_2, String item_url_3, String item_price_1, String item_price_2, String item_price_3) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("barcode_number", barcode);
        contentValues.put("item_name", item_name);
        contentValues.put("item_image", item_image);
        contentValues.put("item_url_1", item_url_1);
        contentValues.put("item_url_2", item_url_2);
        contentValues.put("item_url_3", item_url_3);
        contentValues.put("item_price_1", item_price_1);
        contentValues.put("item_price_2", item_price_2);
        contentValues.put("item_price_3", item_price_3);

        db.insert(HISTORY_TABLE_NAME, null, contentValues);

        return true;
    }

    //Delete single item from Table
    public void deleteEntry(String item_name) {

        SQLiteDatabase ourDatabase = this.getWritableDatabase();
        ourDatabase.delete(FAV_TABLE_NAME, "item_name" + " = '" + item_name + "'", null);
    }

    //    Creating a method to get product from history_prduct table
    public List<DatabaseModel> getDataFromDbForHistory() {

        // Declaring a List object
        List<DatabaseModel> modelList = new ArrayList<>();

//        String for query
        String query = "select * from " + HISTORY_TABLE_NAME;


        SQLiteDatabase db = this.getWritableDatabase();

//        creating cursor object to use query to get the data from table
        Cursor cursor = db.rawQuery(query, null);

//        Condition for set the data in pojo class
        if (cursor.moveToFirst()) {
            do {
//                creating DatabaseModel object to set the list using by Pojo
                DatabaseModel model = new DatabaseModel();
                model.setBarcode((cursor).getString(1));
                model.setProduct_name((cursor).getString(2));
                model.setProduct_img((cursor).getString(3));
                model.setStore_url_1((cursor).getString(4));
                model.setStore_url_2((cursor).getString(5));
                model.setStore_url_3((cursor).getString(6));
                model.setPrice_one((cursor).getString(7));
                model.setPrice_two((cursor).getString(8));
                model.setPrice_three((cursor).getString(9));
                modelList.add(model);
            } while (cursor.moveToNext());
        }


        Log.d("history data", modelList.toString());


        return modelList;
    }

    //    Creating a method to get product from history_prduct table
    public List<DatabaseModel> getDataFromDbForFav() {

        // Declaring a List object
        List<DatabaseModel> modelList = new ArrayList<>();
//        String for query
        String query = "select * from " + FAV_TABLE_NAME;

//      Creating object of SqliteDatabase to access the Database to write by using getWritableDatabase
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                DatabaseModel model = new DatabaseModel();

                model.setBarcode(cursor.getString(1));
                model.setProduct_name(cursor.getString(2));
                model.setProduct_img(cursor.getString(3));
                model.setStore_url_1(cursor.getString(4));
                model.setStore_url_2(cursor.getString(5));
                model.setStore_url_3(cursor.getString(6));
                model.setPrice_one(cursor.getString(7));
                model.setPrice_two(cursor.getString(8));
                model.setPrice_three(cursor.getString(9));

                modelList.add(model);

            } while (cursor.moveToNext());
        }


        Log.d("history data", modelList.toString());


        return modelList;
    }

}