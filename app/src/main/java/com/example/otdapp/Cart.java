package com.example.otdapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cart extends AppCompatActivity {


    Button button;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" PAYMENT METHOD ");
        builder.setIcon(R.drawable.applogo);
        builder.setMessage("1.CASH \n 2.ONLINE PAYMENT");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());

        builder.setPositiveButton("CASH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(Cart.this,"PAY CASH AT THE TIME OF DELIVERY",Toast.LENGTH_SHORT).show();


            }
        });


        builder.setNegativeButton("ONLINE PAYMENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(Cart.this,"PAY ONLINE AT THE TIME OF DELIVERY",Toast.LENGTH_SHORT).show();

            }
        });





        AlertDialog ad = builder.create();

        button = findViewById(R.id.payment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad.show();
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(Cart.this,"ORDER RECIEVED",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });



    }

    public void nexto (View view){
        Intent intent = new Intent(Cart.this,cartView.class);
        startActivity(intent);
    }
}
