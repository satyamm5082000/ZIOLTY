package com.example.otdapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Grocery extends AppCompatActivity {

    SearchView searchView;

    RecyclerView listshowrcy;
    List<Item> productList = new ArrayList<>();

    MainAcitivityAdapter acitivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        productList.add(new Item("Onion","Rs.20 Per Kg"));
        productList.add(new Item("Tomato","Rs.41 Per Kg"));
        productList.add(new Item("Potato","Rs.28 Per Kg"));
        productList.add(new Item("Tender Coconut","Rs.35 Per Kg"));
        productList.add(new Item("Ladies Finger","Rs.70 Per Kg"));
        productList.add(new Item("Orange","Rs.100 Per Kg"));
        productList.add(new Item("Apple","Rs.110 Per Kg"));
        productList.add(new Item("Grapes","Rs.80 Per Kg"));
        productList.add(new Item("Broccoli","Rs.140 Per Kg"));
        productList.add(new Item("Banana","Rs.60 Per Kg"));
        productList.add(new Item("Kiwi","Rs.39 for 3Pc"));
        productList.add(new Item("Lemon","Rs.159 Per Kg"));
        productList.add(new Item("Sugar","Rs.40 Pere Kg"));
        productList.add(new Item("Cow Ghee","Rs.500 Per Ltr"));
        productList.add(new Item("Atta Whole Wheat","Rs.32 Per Kg"));
        productList.add(new Item("Poha Thick","Rs.60 Per Kg"));
        productList.add(new Item("Salt","Rs.19 Per Kg"));
        productList.add(new Item("Sanitary Pads","Rs.240 30Pcs"));
        productList.add(new Item("Dettol","Rs.160 Per 250ml"));
        productList.add(new Item("Soap Bar","Rs.35 Per Pc"));
        productList.add(new Item("Toothpaste","Rs.84 200gm"));
        productList.add(new Item("Coconut oil","Rs.130 300ml"));
        productList.add(new Item("Handwash","Rs.110 250ml"));
        productList.add(new Item("Face Tissue","Rs.50 100Pcs"));
        productList.add(new Item("Dishwash Gel","Rs.85 500ml"));
        productList.add(new Item("Detergent Powder","Rs.110 Per Kg"));
        productList.add(new Item("Floor Cleaner","Rs.164 Per Ltr"));
        productList.add(new Item("Dishwash Bar","Rs.30 450gm"));
        productList.add(new Item("Harpic Toilet","Rs.120 500ml"));
        productList.add(new Item("Fabric Conditioner","Rs.250 Per Ltr"));
        productList.add(new Item("Detergent Bar","Rs.20 250gm"));
        productList.add(new Item("Dry Dog Food","Rs.170 Per Kg"));
        productList.add(new Item("Wet Dog Food ","Rs.160 Per Kg"));
        productList.add(new Item("Dry Dog Food","Rs.280 Per Kg"));
        productList.add(new Item("Wet Cat Food","Rs.180 250gm"));
        productList.add(new Item("Diaper Pants","Rs.160 100Pcs"));
        productList.add(new Item("Extra Absord diaper","Rs.900 50Pcs"));
        productList.add(new Item("Pure Olive Oil","Rs.256 200ml"));
        productList.add(new Item("Fabric Softener","Rs.278 Per Ltr"));
        productList.add(new Item("Baby Food","Rs.530 600gm"));
        productList.add(new Item("Baby Wash","Rs.456 200ml"));




        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        acitivityAdapter = new MainAcitivityAdapter(productList,Grocery.this);
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

