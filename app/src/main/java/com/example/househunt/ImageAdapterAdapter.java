package com.example.househunt;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.househunt.model.House;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapterAdapter extends RecyclerView.Adapter<ImageAdapterAdapter.ViewHolder> {
    private List<String> listdata;
    Activity activity;

    // RecyclerView recyclerView;
    public ImageAdapterAdapter(List<String> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }


//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.custom_image_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String myListData = listdata.get(holder.getAdapterPosition());

        Uri imageFont = Uri.parse(myListData);
        holder.imageView.setImageURI(imageFont);


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}

