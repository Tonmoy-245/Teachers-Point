package com.example.teacherspoint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyDataModel> dataList;
    private Context context;

    public MyAdapter(List<MyDataModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyDataModel data = dataList.get(position);

        // Set data to views
        holder.nameTextView.setText(data.getName());
        holder.addressTextView.setText(data.getAddress());
        holder.contactTextView.setText(data.getContact());

        // Load image using Glide (you need to add the Glide dependency)
        Glide.with(context)
                .load(data.getImageUrl())
                .fitCenter()
                .centerCrop()
                .into(holder.imageView);

        // Set click listener for the "View Profile" button
        holder.viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a web page using an Intent
                String profileLink = data.getProfileLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profileLink));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, addressTextView, contactTextView;
        Button viewProfileButton;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            nameTextView = itemView.findViewById(R.id.item_name);
            addressTextView = itemView.findViewById(R.id.item_address);
            contactTextView = itemView.findViewById(R.id.item_contact);
            viewProfileButton = itemView.findViewById(R.id.item_view_profile);
            cardView = itemView.findViewById(R.id.recyclerView);
        }
    }
}

