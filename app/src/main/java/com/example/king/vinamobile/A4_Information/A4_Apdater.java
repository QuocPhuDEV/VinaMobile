package com.example.king.vinamobile.A4_Information;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.vinamobile.R;

import java.util.List;

public class A4_Apdater extends RecyclerView.Adapter<A4_Apdater.MyViewHolder> {
    private Context context;
    private List<A4_Cls_Products> productsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.a4_item_title);
            price = view.findViewById(R.id.a4_item_price);
            thumbnail = view.findViewById(R.id.a4_item_thumbnail);
        }
    }

    public A4_Apdater(Context context, List<A4_Cls_Products> list) {
        this.context = context;
        this.productsList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.a4_item_cardview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final A4_Cls_Products products = productsList.get(position);
        holder.name.setText(products.getTitle());
        holder.price.setText(products.getPrice());

        Glide.with(context)
                .load(products.getImage())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}
