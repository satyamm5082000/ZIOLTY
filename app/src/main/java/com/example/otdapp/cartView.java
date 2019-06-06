package com.example.otdapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cartView extends AppCompatActivity {


    RecyclerView listshowrcy;
    List<itemInfo> productList = new ArrayList<>();
    TextView txtt;
    TextView txtt1;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    itemInfo itemInfo1;

    cartViewAdapter acitivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        itemInfo1 = new itemInfo();
        acitivityAdapter = new cartViewAdapter(productList,cartView.this);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("cart").child(firebaseUser.getUid());
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                itemInfo1  = dataSnapshot.getValue(itemInfo.class);
                productList.add(itemInfo1);
                acitivityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);

        listshowrcy.setAdapter(acitivityAdapter);
    }
}
