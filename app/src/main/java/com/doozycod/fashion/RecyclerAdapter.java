package com.doozycod.fashion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

//      RecyclerAdapter class to create a view on Recyclerview of HistoryActivity
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    //    Initialising
    Context context;
    static List<DatabaseModel> dbList;

// Constructor for Parsing the List value using Model class

    public RecyclerAdapter(Context context, List<DatabaseModel> dbList) {
        this.dbList = new ArrayList<>();
        this.context = context;
        this.dbList = dbList;

    }

    //    Predefined Method onCreateViewHolder to create a view for Recycler view
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //      providing a view by using LayoutInflater
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_list_view, viewGroup, false);

        //      Giving Access of view to Holder Class
        return new RecyclerHolder(view);

    }


    //      Predefined Method onBindViewHolder use for to bind the Data to the recycler view
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder recyclerHolder, int i) {

        //        Adding product name from the table using list object and DatabaseModel Class
        recyclerHolder.product_name.setText(dbList.get(i).getProduct_name());

        //        Glide for the fetching image type from database and set it to history_Product_image
        Glide.with(context).load(recyclerHolder.history_product_img)
                .load(dbList.get(i).getProduct_img())
                .into(recyclerHolder.history_product_img);
    }

    //        Predefined Method getItemCount to get the position or item numbers in the recycler view
    @Override
    public int getItemCount() {
        return dbList.size();
    }

    //        Holder class with RecyclerviewHolder and OnClickListener
    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView history_product_img;
        TextView product_name, price_one;

        //      Constructor of Holder

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            //      Typecasting
            history_product_img = itemView.findViewById(R.id.history_product_img);
            product_name = itemView.findViewById(R.id.history_product_name);

            //     Settting the OnClickListener to view
            itemView.setOnClickListener(this);

        }

        //           Prefined method of OnClickListener
        @Override
        public void onClick(View v) {

    //           Creating an Intent Object to Start Activity and Parse the Data to another activity
            Intent intent = new Intent(context, ProductInfoActivity.class);

    //          Creating Bundle object to get the Parsable data
            Bundle extras = new Bundle();
    //          Adding data to parse to another activity using Object of Bundle
            extras.putString("barcode", dbList.get(getAdapterPosition()).getBarcode());
            extras.putString("product_name", dbList.get(getAdapterPosition()).getProduct_name());
            extras.putString("product_img", dbList.get(getAdapterPosition()).getProduct_img());
            extras.putString("price_one", dbList.get(getAdapterPosition()).getPrice_one());
            extras.putString("price_two", dbList.get(getAdapterPosition()).getPrice_two());
            extras.putString("price_three", dbList.get(getAdapterPosition()).getPrice_three());
            extras.putString("url_1", dbList.get(getAdapterPosition()).getStore_url_1());
            extras.putString("url_2", dbList.get(getAdapterPosition()).getStore_url_2());
            extras.putString("url_3", dbList.get(getAdapterPosition()).getStore_url_3());
            extras.putInt("position", getAdapterPosition());
    //            Adding bundle object to intent Object to using putExtras to get the data in Another Activity
            intent.putExtras(extras);

    //          Starting Another Activity using StartActvity and Intent Object
            context.startActivity(intent);

        }
    }

}
