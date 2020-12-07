package com.example.househunt;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;


import com.example.househunt.model.House;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import fr.quentinklein.slt.LocationTracker;
import fr.quentinklein.slt.ProviderError;


public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private List<House> notesList = new ArrayList<>();
    AppCompatImageView image1;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude = "", longitude = "";
    MultiplePermissionsListener permissionListener;

    boolean isPermissionAllowed=false;

    private TextView location, latLong, diff;
    private Double lati = null, longi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LocationTracker locationTracke = new LocationTracker(5 * 60 * 1000, 100f, true, true, true);



        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

        permissionListener = new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        };

        Dexter.withContext(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE)
                .withListener(permissionListener).check();



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationTracke.startListening(getApplicationContext());

        locationTracke.addListener(new LocationTracker.Listener() {
            @Override
            public void onLocationFound(Location location) {
                lati= location.getLatitude();
                longi= location.getLongitude();
            }

            @Override
            public void onProviderError(ProviderError providerError) {

            }
        });
    }







    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                lati = locationGPS.getLatitude();
                longi = locationGPS.getLongitude();
                latitude = String.valueOf(lati);
                longitude = String.valueOf(longi);
            } else {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        }
    }

    public void viewProperty(View view) {

        Intent intent = new Intent(getApplicationContext(), ViewPropertyListing.class);
        startActivity(intent);
    }


    public void addProperty(View view) {




        if (lati == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                OnGPS();
            } else {
                getLocation();
            }
            Dexter.withContext(this)
                    .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE)
                    .withListener(permissionListener).check();
        } else {
            Intent intent = new Intent(getApplicationContext(), AddPropertyActivity.class);
            intent.putExtra("latitude",lati.toString());
            intent.putExtra("longitude",longi.toString());
            startActivity(intent);
        }


    }

}