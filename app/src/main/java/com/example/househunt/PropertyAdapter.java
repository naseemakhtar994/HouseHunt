package com.example.househunt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.househunt.model.House;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private List<House> listdata;
    Activity activity;

    // RecyclerView recyclerView;
    public PropertyAdapter(List<House> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }




//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_item_porperty, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final House myListData = listdata.get(holder.getAdapterPosition());

        ArrayList<String> images = new Gson().fromJson(myListData.getImagesArray(), ArrayList.class);
        Uri imageFont = Uri.parse(images.get(0));
        holder.textViewName.setText(myListData.getName());
        holder.imageView.setImageURI(imageFont);
        holder.textViewDescription.setText(myListData.getDescription());
        holder.textViewType.setText(myListData.getType());
        holder.textViewLocation.setText(myListData.getLocation());

        holder.textViewPrice.setText("£" + myListData.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailsViewActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("house", (Serializable) myListData);
                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewLocation;

        public TextView textViewPrice;
        public TextView textViewType;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.textViewLocation);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            this.textViewType = (TextView) itemView.findViewById(R.id.textViewType);

        }
    }
}

