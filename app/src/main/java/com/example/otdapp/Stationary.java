package com.example.otdapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stationary extends AppCompatActivity {


    SearchView searchView;

    RecyclerView listshowrcy;
    List<Item> productList = new ArrayList<>();

    MainAcitivityAdapter acitivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationary);

        productList.add(new Item("Memo Pads","Rs.40"));
        productList.add(new Item("Day Book","Rs.200"));
        productList.add(new Item("Telephone Book","Rs.100"));
        productList.add(new Item("Name card Book","Rs.80"));
        productList.add(new Item("Letter Paper","Rs.25"));
        productList.add(new Item("Stamp albums","Rs.220"));
        productList.add(new Item("Multipurpose handbook","Rs.180"));
        productList.add(new Item("Adhesive labels","Rs.180"));
        productList.add(new Item("Ballpoint pens","Rs.10"));
        productList.add(new Item("Gel Ink Pen","Rs.10"));
        productList.add(new Item("Marker Pens","Rs.25"));
        productList.add(new Item("Whiteboard Pens","Rs.30"));
        productList.add(new Item("Sign Pens","Rs.80"));
        productList.add(new Item("Craft Pens","Rs.40"));
        productList.add(new Item("Pencils","Rs.5"));
        productList.add(new Item("Water color pens","Rs.20"));
        productList.add(new Item("Lever clip file","Rs.25"));
        productList.add(new Item("File Tray","Rs.180"));
        productList.add(new Item("File box","Rs.200"));
        productList.add(new Item("Report cover","Rs.50"));
        productList.add(new Item("File bags","Rs.350"));
        productList.add(new Item("Hanging folder","Rs.280"));
        productList.add(new Item("Data book","Rs.120"));
        productList.add(new Item("Transparent cover","Rs.20"));
        productList.add(new Item("Frame","Rs.40"));
        productList.add(new Item("Rice paper","Rs.20"));
        productList.add(new Item("Brush Pencils","Rs.15"));
        productList.add(new Item("Crayons","Rs.50"));
        productList.add(new Item("Drawing paper","Rs.15"));
        productList.add(new Item("Plaster Realia","Rs.30"));
        productList.add(new Item("Roll film","Rs.200"));
        productList.add(new Item("Photo frame","Rs.140"));
        productList.add(new Item("Toy video camera","Rs.8000"));
        productList.add(new Item("Optical Apparatus","Rs.750"));
        productList.add(new Item("Optical Stsandard Gauges","Rs.500"));
        productList.add(new Item("Jewelry","Rs.200"));
        productList.add(new Item("Crystal ring","Rs.180"));
        productList.add(new Item("Wooden keychain","Rs.80"));
        productList.add(new Item("Sculpture","Rs.40"));
        productList.add(new Item("Glasses","Rs.120"));



        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        acitivityAdapter = new MainAcitivityAdapter(productList,Stationary.this);
        listshowrcy.setAdapter(acitivityAdapter);


    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.searchfile,menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView =(SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final List<Item> filtermodelist = filter(productList,s);
                acitivityAdapter.setFilter(filtermodelist);
                return true;
            }
        });
        return true;
    }

    private List<Item> filter (List<Item> p1,String query){
        query = query.toLowerCase();
        final List<Item> filterModeList = new ArrayList<>();
        for (Item mode1:p1)
        {
            final String text = mode1.getName().toLowerCase();
            if (text.startsWith(query))
                filterModeList.add(mode1);
        }
        return filterModeList;
    }

    private void changeSearchViewTextColor (View view)
    {
        if (view != null)
        {
            if (view instanceof TextView)
            {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) view;

                for (int i =0; i<viewGroup.getChildCount(); i++)
                {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }

        }
    }

}
