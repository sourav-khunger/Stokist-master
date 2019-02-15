package com.doozycod.fashion;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.doozycod.fashion.Adapter.GridAdapter;

import java.util.ArrayList;

public class Discover extends Activity {

    DBHelper dbHelper;
    LinearLayout history_button, scan_button, favorite_button, home_button;

    GridView gridView;
    ArrayList<Model> list;
    //    Create Arrays for Product Image and company Logo
    public static String[] product_url = {"https://www.flipkart.com/puma-solid-men-turtle-neck-black-t-shirt/p/itmf4f9vdr6fwhzh?pid=TSHF4YY7CUQGNHH2&lid=LSTTSHF4YY7CUQGNHH2HB2ARX&marketplace=FLIPKART&srno=b_1_26&otracker=nmenu_sub_Men_0_T-Shirts&fm=organic&iid=2e1ab930-4272-41f5-8ec7-406ecaa8a88e.TSHF4YY7CUQGNHH2.SEARCH&ppt=StoreBrowse&ppn=Store&ssid=3957yai7o00000001549961617494",
            "https://www.myntra.com/jackets/teesort/teesort-men-black-solid-biker-jacket/7708373/buy",
            "https://www.amazon.in/gp/product/B07L3K4NMQ/ref=s9_acsd_newrz_hd_bw_b29C1vT_c_x_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-11&pf_rd_r=XZBAMH78G9R0ADJP8MD7&pf_rd_t=101&pf_rd_p=30cf830b-0983-55ff-956b-faba9ece27a4&pf_rd_i=1968120031",
            "https://paytmmall.com/routeen-men-s-medium-blue-cotton-spandex-jeans-CMPLXAPPROUTEEN-MEN-SMAR25511457D9F0E-pdp?product_id=196307702&channel=WEB&discoverability=online&src=grid&svc=2&tracker=%7C%7C%7C%7C%2Fg%2Fmen-youth%2Fmen-jeans-under-699-llpid-193270%7C193270%7C2%7C%7C%7C%7C&site_id=1",
            "https://www.jabong.com/dorothy-perkins-grey-melange-ribbed-pullover-7775994.htm?pos=31",
            "https://www.flipkart.com/shopybucket-men-solid-party-black-white-shirt/p/itmf7aymrw3nakhy?pid=SHTF7ANNSDX6FFUA&lid=LSTSHTF7ANNSDX6FFUAMXV7SM&marketplace=FLIPKART&srno=b_1_1&otracker=nmenu_sub_Men_0_Shirts&fm=organic&iid=fdec0237-00f5-430f-bf8a-db0800305416.SHTF7ANNSDX6FFUA.SEARCH&ppt=StoreBrowse&ppn=Store&ssid=ikssx07j9s0000001550225226211",
            "https://www.amazon.in/STYLE-QUOTIENT-Women-Blue-Striped/dp/B07KSXJ2NW/ref=pd_rhf_se_s_pd_crcd_0_4/262-4456445-2743222?_encoding=UTF8&pd_rd_i=B07KSXJ2NW&pd_rd_r=34811bc4-9454-42d2-92f6-f5cf0293087d&pd_rd_w=sbUyX&pd_rd_wg=ORuQB&pf_rd_p=6376ac07-6b52-4317-b621-3080a4a917e5&pf_rd_r=X0SAY14W8AF8PH998GJM&refRID=X0SAY14W8AF8PH998GJM",
            "https://paytmmall.com/sareemall-cream-festive-wear-patola-silk-plain-solid-saree-with-unstitched-blouse-APPSAREEMALL-CRSARE236871C2C5832-pdp?channel=WEB&discoverability=online&src=grid&svc=2&tracker=%7C%7C%7C%7C%2Fg%2Fwomen-ethnic%2Fsarees-upto-70-cashback-llpid-134249%7C134249%7C2%7C%7C%7C%7C"};
    public static String[] product_image = {"https://rukminim1.flixcart.com/image/880/1056/jflfgcw0/t-shirt/h/h/2/m-51624801-puma-original-imaf4fafvbwq7cme.jpeg",
            "https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/7708373/2018/10/29/1b954a53-eb13-49c1-8df3-91a72d5821961540802729545-Teesort-PU-Leather-Jacket-with-Fur-7761540802729384-1.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/71MYFa29GXL._UL1500_.jpg",
            "https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPROUTEEN-MEN-SMAR25511457D9F0E/a_0..jpg",
            "https://assets.jassets.com/h_600,q_95,w_440/v1/assets/images/productimage/2018/11/12/4db7d006-3b0a-458a-acf6-350a2899c9d21542027335775-1.jpg",
            "https://rukminim1.flixcart.com/image/880/1056/jk4bngw0/shirt/e/j/x/xl-stin22-shopybucket-original-imaf7aymu6d86ggg.jpeg",
            "https://images-na.ssl-images-amazon.com/images/I/71vMUOTuKxL._UL1295_.jpg",
            "https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPSAREEMALL-CRSARE236871C2C5832/a_5..jpg"};
    public static int[] company_logo = {
            R.drawable.flipkart,
            R.drawable.myntra,
            R.drawable.amazon,
            R.drawable.paytm,
            R.drawable.jabong,
            R.drawable.flipkart,
            R.drawable.amazon,
            R.drawable.paytm};

    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      Checking that run is first time of the app or not
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

//      Condition for Checking isFirstRun or not
        if (isFirstRun) {
            startActivity(new Intent(Discover.this, Splash.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

//      Requesting Permission for the Camera
        if (ContextCompat.checkSelfPermission(Discover.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    Discover.this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions((Activity) Discover.this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSION_REQUEST_CODE);
            }

        }

        setContentView(R.layout.activity_scan_now);

//       TypeCasting
        gridView = findViewById(R.id.rv_products);
        scan_button = findViewById(R.id.scan_button);
        home_button = findViewById(R.id.home_button);
        history_button = findViewById(R.id.history_button);
        favorite_button = findViewById(R.id.fav_button);


        dbHelper = new DBHelper(this);
        dbHelper.insertContact_one();
        dbHelper.insertContact_two();
        dbHelper.insertContact_three();

        gridView.setAdapter(new GridAdapter(this, product_image, company_logo, product_url));

//        Calling OnClickListener to go to AddtoFavActivity when Favorite Button is Pressed
        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Discover.this, AddToFavActivity.class));
                finish();

            }
        });

//        Calling OnClickListener to go to AddtoFavActivity when History Button is Pressed
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Discover.this, HistoryActivity.class));
                finish();
            }
        });
//        Calling OnClickListener to go to AddtoFavActivity when Scanner Button is Pressed
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Discover.this, BarCodeScanActivity.class));
                finish();


            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}




