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

//      AddToFavAdapter class to create a view on Recyclerview of AddToFavActivity
public class AddToFavAdapter extends RecyclerView.Adapter<AddToFavAdapter.Holder> {
//    Initialising
    private Context context;
    private static List<DatabaseModel> addToFavModels;
// Constructor for Parsing the List value using Model class
    AddToFavAdapter(Context context, List<DatabaseModel> addToFavModels) {
        this.context = context;
        AddToFavAdapter.addToFavModels = new ArrayList<>();
        AddToFavAdapter.addToFavModels = addToFavModels;
    }

//    Predefined Method onCreateViewHolder to create a view for Recycler view
    @NonNull
    @Override
    public AddToFavAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

//    providing a view by using LayoutInflater
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_to_fav_list_view, viewGroup, false);
//      Giving Access of view to Holder Class
        return new Holder(view);
    }
//Predefined Method onBindViewHolder use for to bind the Data to the recycler view
    @Override
    public void onBindViewHolder(@NonNull AddToFavAdapter.Holder holder, int position) {

        holder.product_name.setText(addToFavModels.get(position).getProduct_name());

//        Glide for the fetching image type from database and set it to Product_image
        Glide.with(context)
                .load(addToFavModels.get(position).getProduct_img())
                .into(holder.product_img);
    }
//        Predefined Method getItemCount to get the position or item numbers in the recycler view
    @Override
    public int getItemCount() {

        return addToFavModels.size();
    }
//  Holder class with RecyclerviewHolder and OnClickListener
    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView product_name;
        ImageView product_img;

//      Constructor of Holder
        Holder(@NonNull View itemView) {

            super(itemView);
//          Typecasting
            product_img = itemView.findViewById(R.id.atf_product_img);
            product_name = itemView.findViewById(R.id.atf_product_name);
//            Settting the OnClickListener to view
            itemView.setOnClickListener(this);
        }
// Prefined method of OnClickListener
        @Override
        public void onClick(View v) {
//            Creating an Intent Object to Start Activity and Parse the Data to another activity
            Intent intent = new Intent(context, ProductInfoActivity.class);
//          Creating Bundle object to get the Parsable data
            Bundle extras = new Bundle();
//          Adding data to parse to another activity using Object of Bundle
            extras.putString("barcode", addToFavModels.get(getAdapterPosition()).getBarcode());
            extras.putString("product_name", addToFavModels.get(getAdapterPosition()).getProduct_name());
            extras.putString("product_img", addToFavModels.get(getAdapterPosition()).getProduct_img());
            extras.putString("price_one", addToFavModels.get(getAdapterPosition()).getPrice_one());
            extras.putString("price_two", addToFavModels.get(getAdapterPosition()).getPrice_two());
            extras.putString("price_three", addToFavModels.get(getAdapterPosition()).getPrice_three());
            extras.putString("url_1", addToFavModels.get(getAdapterPosition()).getStore_url_1());
            extras.putString("url_2", addToFavModels.get(getAdapterPosition()).getStore_url_2());
            extras.putString("url_3", addToFavModels.get(getAdapterPosition()).getStore_url_3());
            extras.putInt("position", getAdapterPosition());

//          Adding bundle object to intent Object to using putExtras to get the data in Another Activity
            intent.putExtras(extras);
//          Starting Another Activity using StartActvity and Intent Object
            context.startActivity(intent);
        }
    }
}
