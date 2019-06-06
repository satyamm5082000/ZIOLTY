package com.example.otdapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class cartViewAdapter extends RecyclerView.Adapter<cartViewAdapter.Holderview> {



    private List<itemInfo> productList;
    private Context context;


    public cartViewAdapter (List<itemInfo> productList,Context context)
    {
        this.productList = productList;
        this.context = context;
    }
    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleradapter,viewGroup,false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview myViewHolder, int i) {

        myViewHolder.m_name.setText(productList.get(i).getName());
        myViewHolder.m_price.setText(productList.get(i).getPrice());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class Holderview extends RecyclerView.ViewHolder {

        TextView m_name;
        TextView m_price;

        public  Holderview (@NonNull View itemview)
        {
            super(itemview);
            m_name =  itemview.findViewById(R.id.text);
            m_price =  itemview.findViewById(R.id.text2);

        }
    }
}



