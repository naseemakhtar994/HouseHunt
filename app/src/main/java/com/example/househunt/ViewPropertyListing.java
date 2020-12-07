package com.example.househunt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.househunt.model.House;
import com.example.househunt.model.PriceHightoLowComparator;
import com.example.househunt.model.PriceLowToHighComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPropertyListing extends AppCompatActivity {

    private DatabaseHelper db;
    private List<House> notesList = new ArrayList<>();
    private List<House> arrayListtemp = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    AppCompatImageView image1;

    EditText edit_query;
    RecyclerView recycleView;
    Button buttonLowToHigh, buttonHighToLow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_property);
        db = new DatabaseHelper(this);
        recycleView = findViewById(R.id.recycleView);
        buttonLowToHigh = findViewById(R.id.buttonLowtoHigh);
        buttonHighToLow = findViewById(R.id.buttonHighToLow);
        edit_query = findViewById(R.id.edit_query);
        notesList.addAll(db.getAllNotes());
        arrayListtemp.addAll(db.getAllNotes());
        PropertyAdapter adapter = new PropertyAdapter(notesList, ViewPropertyListing.this);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recycleView.setAdapter(adapter);
//        progressbar.setVisibility(View.GONE);
//        db.insertNote(new House("Testing house","1 Bhk","descriotion"));


        edit_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchProperty(editable.toString());
            }
        });

        buttonLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHighToLow.setBackground(getResources().getDrawable(R.drawable.unselected_buttonborder));
                buttonHighToLow.setText("Price: High-to-Low");

                if (buttonLowToHigh.getText().toString().contains("☑")) {
                    buttonLowToHigh.setText("Price: Low-to-High");
                    buttonLowToHigh.setBackground(getResources().getDrawable(R.drawable.unselected_buttonborder));
                    PropertyAdapter adapter = new PropertyAdapter(notesList, ViewPropertyListing.this);
                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recycleView.setAdapter(adapter);
                } else {

                        Collections.sort(arrayListtemp, new PriceLowToHighComparator());

                    buttonLowToHigh.setText("☑ Price: Low-to-High");
                    buttonLowToHigh.setBackground(getResources().getDrawable(R.drawable.buttonborder));

                    PropertyAdapter adapter = new PropertyAdapter(arrayListtemp, ViewPropertyListing.this);
                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recycleView.setAdapter(adapter);

                }
            }
        });


        buttonHighToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLowToHigh.setBackground(getResources().getDrawable(R.drawable.unselected_buttonborder));
                buttonLowToHigh.setText("Price: Low-to-High");

                if (buttonHighToLow.getText().toString().contains("☑")) {
                    buttonHighToLow.setText("Price: High-to-Low");
                    buttonHighToLow.setBackground(getResources().getDrawable(R.drawable.unselected_buttonborder));
                    PropertyAdapter adapter = new PropertyAdapter(notesList, ViewPropertyListing.this);
                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recycleView.setAdapter(adapter);
                } else {

                    Collections.sort(arrayListtemp, new PriceHightoLowComparator());

                    buttonHighToLow.setText("☑ Price: High-to-Low");
                    buttonHighToLow.setBackground(getResources().getDrawable(R.drawable.buttonborder));

                    PropertyAdapter adapter = new PropertyAdapter(arrayListtemp, ViewPropertyListing.this);
                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recycleView.setAdapter(adapter);

                }
            }
        });


    }

    private void searchProperty(String serach) {

        ArrayList data = new ArrayList();

        for (House house : notesList) {

            if (house.getLocation().toLowerCase().contains(serach.toLowerCase())) {
                data.add(house);
            }

        }
        PropertyAdapter adapter = new PropertyAdapter(data, ViewPropertyListing.this);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recycleView.setAdapter(adapter);
    }


    ArrayList selectedTypes = new ArrayList();

    public void getFilterAMinities(final ArrayList aminitis) {


        Log.e("", "");
        selectedTypes = aminitis;
        ArrayList data = new ArrayList();

        for (House zoneSnapshot : notesList) {


            for (Object itemaminities : aminitis) {
                if (zoneSnapshot.getType().toLowerCase().contains(itemaminities.toString().toLowerCase())) {
                    data.add(zoneSnapshot);
                    break;
                }
            }

        }
        PropertyAdapter adapter = new PropertyAdapter(data, ViewPropertyListing.this);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recycleView.setAdapter(adapter);


    }

    public void showCategories(View view) {

// add a checkbox list

        CheckBoxMultiSelect checkBoxMultiSelect = new CheckBoxMultiSelect();
        checkBoxMultiSelect.showDiaog(ViewPropertyListing.this);
    }
}
