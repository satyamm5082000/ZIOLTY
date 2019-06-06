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

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class Canteen extends AppCompatActivity {

    SearchView searchView;

    RecyclerView listshowrcy;
    List<Item> productList = new ArrayList<>();

    MainAcitivityAdapter acitivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);



        productList.add(new Item("Sweet Lassi","Rs.80"));
        productList.add(new Item("Masala Lemonade","Rs.80"));
        productList.add(new Item("Butter Milk","Rs.60"));
        productList.add(new Item("Cold Coffee","Rs.100"));
        productList.add(new Item("Tiramisu","Rs.200"));
        productList.add(new Item("Key Lime Pie","Rs.220"));
        productList.add(new Item("Kesari Rasmalai","Rs.110"));
        productList.add(new Item("Vanilla Ice Cream","Rs.40"));
        productList.add(new Item("American Nuts Ice Cream","Rs.50"));
        productList.add(new Item("Pastry","Rs.30"));
        productList.add(new Item("Cheese Platter","Rs.70"));
        productList.add(new Item("Paneer Roll","Rs.60"));
        productList.add(new Item("Sandwiches","Rs.30"));
        productList.add(new Item("Veg Burgers","Rs.85"));
        productList.add(new Item("Sweet Corn Vegetable Soup","Rs.80"));
        productList.add(new Item("Tom Yum Vegetable Soup","Rs.90"));
        productList.add(new Item("Crispy Fried Wok Toss","Rs.110"));
        productList.add(new Item("Wok Fried Prawns","Rs.140"));
        productList.add(new Item("Vegetable  Hakka Noodles","Rs.100"));
        productList.add(new Item("Chicken Hakka Noodles","Rs.160"));
        productList.add(new Item("Risotto","Rs.240"));
        productList.add(new Item("Pizzas","Rs.220"));
        productList.add(new Item("Pastas","Rs.160"));
        productList.add(new Item("Round Dressy Burger","Rs.85"));
        productList.add(new Item("Donut Pizza Mania","Rs.90"));
        productList.add(new Item("Chicken Hot Dog","Rs.80"));
        productList.add(new Item("Mama Mia Spaghetti Pasta","Rs.90"));
        productList.add(new Item("Khoya Paneer","Rs.140"));
        productList.add(new Item("Shahi Paneer","Rs.160"));
        productList.add(new Item("Bhindi Masala","Rs.170"));
        productList.add(new Item("Dal Fry","Rs.100"));
        productList.add(new Item("Chana Masala","Rs.140"));
        productList.add(new Item("Veg Pulao","Rs.138"));
        productList.add(new Item("Jeera Rice","Rs.120"));
        productList.add(new Item("Dum Aloo","Rs.60"));
        productList.add(new Item("Chole","Rs.60"));
        productList.add(new Item("Butter Roti","Rs.8"));



        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        acitivityAdapter = new MainAcitivityAdapter(productList,Canteen.this);
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
