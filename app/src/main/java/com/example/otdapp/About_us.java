

package com.example.otdapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class About_us extends AppCompatActivity {
    Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        button1 = findViewById(R.id.satyam);
        button2 = findViewById(R.id.satya);
        button3 = findViewById(R.id.rohit);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SATYAM MISHRA");
        builder.setIcon(R.drawable.applogo);
        builder.setMessage("    ANDROID DEVELOPER    ");

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("SATYA DHWAJ");
        builder1.setIcon(R.drawable.applogo);
        builder1.setMessage("   WEB DEVELOPER   ");

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("ROHIT KUMAR");
        builder2.setIcon(R.drawable.applogo);
        builder2.setMessage("   UI DESIGNER   ");

        AlertDialog ad = builder.create();
        AlertDialog ad1 = builder1.create();
        AlertDialog ad2 = builder2.create();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad1.show();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad2.show();

            }
        });

//        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//            }
//        });
//
//        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//            }
//        });
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//            }
//        });

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            }
        });
        builder1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            }
        });
        builder2.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            }
        });
    }
}
