package com.doozycod.fashion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.doozycod.fashion.Discover;
import com.doozycod.fashion.R;

public class GridAdapter extends BaseAdapter {

    String[] product_img;
    String[] url;
    Context context;
    int[] companylogo;
    private static LayoutInflater inflater = null;

    public GridAdapter(Discover mainActivity, String[] product_image, int[] company_logo, String[] url) {
        // TODO Auto-generated constructor stub
        context = mainActivity;
        product_img = product_image;
        companylogo = company_logo;
        this.url = url;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return product_img.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {

        ImageView productimg, company_logo;
        LinearLayout layout;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_view_all_products, null);
        holder.company_logo = rowView.findViewById(R.id.company_logo);
        holder.productimg = (ImageView) rowView.findViewById(R.id.all_product_img);
        holder.layout = rowView.findViewById(R.id.layout);
        Glide.with(context).load(product_img[position]).into(holder.productimg);

        holder.company_logo.setImageResource(companylogo[position]);
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse(url[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

        return rowView;
    }

}