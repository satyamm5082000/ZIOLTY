package com.example.otdapp;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notification extends AppCompatActivity {

     ExpandableListAdapter listAdapter ;
     ExpandableListView expandableListView;
     List<String > list;
     private HashMap<String,List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        expandableListView = findViewById(R.id.lvExp);
        prepareListData ();
        listAdapter = new ExpandableListAdapter(this,list,listHashMap);
        expandableListView.setAdapter(listAdapter);

      }

    private void prepareListData() {
        list = new ArrayList<>();
        listHashMap = new HashMap<>();


        list.add("Notification 1");
        list.add("Notification 2");
        list.add("Notification 3");

        List <String> notification1 = new ArrayList<>();
        notification1.add("Ever wondered why to use 3-4 apps for your grocery, food, and other daily needs. Introducing Ziolty, an online company which has solution to your every daily need. ");

        List <String> notifictaion2 = new ArrayList<>();
        notifictaion2.add("Amount of 100 rs has credited to your account. ");

        List <String> notification3 = new ArrayList<>();
        notification3.add("Thank You ......  keep using ZIOLTY...!!!!!! ");

        listHashMap.put(list.get(0),notification1);
        listHashMap.put(list.get(1),notifictaion2);
        listHashMap.put(list.get(2),notification3);



    }


}
