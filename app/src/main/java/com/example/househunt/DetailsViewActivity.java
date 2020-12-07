package com.example.househunt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.househunt.model.House;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DetailsViewActivity extends AppCompatActivity {
RecyclerView recyclerView;
TextView textViewTitle,textViewPrice,textViewTyps,textViewLocation,textViewDescription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details);
        textViewTitle=findViewById(R.id.textViewTitle);
        textViewPrice=findViewById(R.id.textViewPrice);
        textViewTyps=findViewById(R.id.textViewTyps);
        textViewLocation=findViewById(R.id.textViewLocation);
        textViewDescription=findViewById(R.id.textViewDescription);
        recyclerView=findViewById(R.id.recyclerView);
        House house= (House) getIntent().getSerializableExtra("house");
        ArrayList<String> images = new Gson().fromJson(house.getImagesArray(), ArrayList.class);

        textViewTitle.setText(house.getName());
        textViewPrice.setText("£" +house.getPrice());
        textViewTyps.setText(house.getType());
        textViewLocation.setText(house.getLocation());
        textViewDescription.setText(house.getDescription());
        ImageAdapterAdapter adapter = new ImageAdapterAdapter(images, DetailsViewActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,true));
        recyclerView.setAdapter(adapter);

    }

    public void call(View view) {
        House house= (House) getIntent().getSerializableExtra("house");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + house.getContact()));
        startActivity(intent);
    }

    public void message(View view) {
        House house= (House) getIntent().getSerializableExtra("house");

        Intent I =new Intent(Intent.ACTION_VIEW);

        I.setData(Uri.parse("smsto:"));
        I.setType("vnd.android-dir/mms-sms");
        I.putExtra("address", new String (house.getContact()));
        I.putExtra("sms_body","Enter your Sms here..");

        try
        {
            startActivity(I);
            Log.i("Sms Send","");
        }
        catch(Exception e)
        {
            Toast.makeText(DetailsViewActivity.this,"Sms not send",Toast.LENGTH_LONG).show();
        }
    }
}
