package com.example.househunt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.househunt.model.House;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddPropertyActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<House> notesList = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    AppCompatImageView image1;

    EditText editTextTitle, editTextDescription, editTextPrice,editTextLocality,editTextContact;
    Spinner spnnerPropertyTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_property);
        db = new DatabaseHelper(this);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        spnnerPropertyTypes = findViewById(R.id.spnnerPropertyTypes);
        editTextLocality = findViewById(R.id.editTextLocality);
        editTextContact = findViewById(R.id.editTextContact);

        notesList.addAll(db.getAllNotes());


    }

    public void SubmitData(View view) {

        String imagesArray = new Gson().toJson(images);
        String name = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String price = editTextPrice.getText().toString();
        String types = spnnerPropertyTypes.getSelectedItem().toString();
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");
        String location = editTextLocality.getText().toString();
        String contact = editTextContact.getText().toString();

        if (validateForm(name, price, description,location,contact)) {
            new Common().showDialog(AddPropertyActivity.this, getString(R.string.add_property_successfully));
            db.insertNote(new House(name, types, description, price, imagesArray,latitude,longitude,location,contact));

        }

    }

    private boolean validateForm(String name, String price, String description,String locality,String contact) {
        if (TextUtils.isEmpty(name)) {
            editTextTitle.setError(getString(R.string.please_enter_title));
            return false;
        } else if (TextUtils.isEmpty(contact)) {
            editTextContact.setError("Please Enter contact no");
            return false;
        } else if (contact.length() !=10) {
            editTextContact.setError("Please Enter valid contact no");
            return false;
        } else if (TextUtils.isEmpty(description)) {
            editTextDescription.setError(getString(R.string.please_enter_description));
            return false;
        } else if (TextUtils.isEmpty(price)) {
            editTextPrice.setError(getString(R.string.please_enter_price));
            return false;
        } else if (TextUtils.isEmpty(locality)) {
            editTextLocality.setError(getString(R.string.please_enter_locality));
            return false;
        } else if (images.size() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.please_select_image), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            editTextTitle.setError(null);
            editTextPrice.setError(null);
            editTextDescription.setError(null);
            editTextLocality.setError(null);
            editTextContact.setError(null);
            return true;
        }
    }


    public void showImage(View imageView) {
        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
        image1 = (AppCompatImageView) imageView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
//            image1.setImageURI(fileUri);


            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            //You can also get File Path from intent
            String filePath = ImagePicker.Companion.getFilePath(data);

            Uri myUri = Uri.parse(data.getData().toString());

            images.add(data.getData().toString());
            image1.setImageURI(myUri);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}