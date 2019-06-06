package com.example.otdapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainAcitivityAdapter extends RecyclerView.Adapter<MainAcitivityAdapter.Holderview> {
    FirebaseAuth mAuth ;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<Item> productList;
    private Context context;



    public MainAcitivityAdapter (List<Item> productList,Context context)
    {
        this.productList = productList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customitem,viewGroup,false);

        return new Holderview(layout) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, int i) {

         holderview.m_name.setText(productList.get(i).getName());
         holderview.m_price.setText(productList.get(i).getPrice());

        holderview.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Click On "+productList.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holderview.aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){

                itemInfo itemInf = new itemInfo(productList.get(i).getName(),productList.get(i).getPrice());
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                databaseReference.child("cart").child(user.getUid()).push().setValue(itemInf);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public void setFilter (List<Item> listItem)
    {
        productList = new ArrayList<>();
        productList.addAll(listItem);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder
    {
        TextView m_name;
        Switch aSwitch;
        TextView m_price;

       public  Holderview (@NonNull View itemview)
        {
            super(itemview);
            m_name =  itemview.findViewById(R.id.product_title);
            m_price =  itemview.findViewById(R.id.product_image);
            aSwitch = itemview.findViewById(R.id.switcher);
        }

    }
}
