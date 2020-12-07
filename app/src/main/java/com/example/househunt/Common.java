package com.example.househunt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Common {
    public void showDialog(final Activity activity,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setTitle(activity.getResources().getString(R.string.app_name));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if(activity!=null) activity.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showMessage(final Activity activity,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setTitle("Blood Wave");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void CreatAccount(final Activity activity,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setTitle("Blood Wave");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(activity,AddPropertyActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
