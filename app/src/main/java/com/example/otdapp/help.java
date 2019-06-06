package com.example.otdapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class help extends AppCompatActivity {

    ExpandableListAdapter listAdapter ;
    ExpandableListView expandableListView;
    List<String > list;
    private HashMap<String,List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        expandableListView = findViewById(R.id.lvExp);
        prepareListData ();
        listAdapter = new ExpandableListAdapter(this,list,listHashMap);
        expandableListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        list = new ArrayList<>();
        listHashMap = new HashMap<>();


        list.add("Ques:- I want parnter my restaurant or shop with Ziolty?");
        list.add("Ques:- I want to cancel my order?");
        list.add("Ques:- Is the minimum order value?");
        list.add("Ques:- Do you Charge for delivery?");
        list.add("Ques:- How long do you take to deliver?");
        list.add("Ques:- I want to explore career opportunities with Ziolty?");
        list.add("Ques:- I want an invoice for my order?");

        List <String> notification1 = new ArrayList<>();
        notification1.add("Partner with us \n Email us:- \n 2018kucp1001@iiitkota.ac.in \n 2018kucp1003@iiitkota.ac.in \n 2018kuec2045@iiitkota.ac.in \n (we will revert within 12-24 hrs)");

        List <String> notifictaion2 = new ArrayList<>();
        notifictaion2.add("We will do over best to accommodate your request if the order is not placed to over delivery partners.\n" +
                "Please note that we will have a right to charge a cancellation fee up to full order value to compensate over delivery partners if your order has been confirmed.\n");

        List <String> notification3 = new ArrayList<>();
        notification3.add("We have no minimum order value and you can order for any amount.\n");


        List <String> notification4 = new ArrayList<>();
        notification4.add("Delivery fee varies from place to place and is applicable if order value is below certain amount.\n" +
                "Delivery fee(if any) is specified on the 'Review order' page.\n");


        List <String> notification5 = new ArrayList<>();
        notification5.add("Standard delivery time vary by the location selected and prevailing condition.\n" +
                "Once you select your location an estimation delivery time is mentioned.\n");


        List <String> notification6 = new ArrayList<>();
        notification6.add(" Join our team\n" +

                "EMAIL US:- \n 2018kucp1001@iiitkota.ac.in \n 2018kucp1003@iiitkota.ac.in \n 2018kuec2045@iiitkota.ac.in \n " +
                "(we will revert within 12-24 hrs)\n");

        List <String> notification7 = new ArrayList<>();
        notification7.add("EMAIL US:- \n 2018kucp1001@iiitkota.ac.in \n 2018kucp1003@iiitkota.ac.in \n 2018kuec2045@iiitkota.ac.in \n" +
                "(we will revert within 12hrs)");



        listHashMap.put(list.get(0),notification1);
        listHashMap.put(list.get(1),notifictaion2);
        listHashMap.put(list.get(2),notification3);
        listHashMap.put(list.get(3),notification4);
        listHashMap.put(list.get(4),notification5);
        listHashMap.put(list.get(5),notification6);
        listHashMap.put(list.get(6),notification7);



    }
}
