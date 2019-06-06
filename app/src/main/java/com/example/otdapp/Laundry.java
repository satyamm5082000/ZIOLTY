package com.example.otdapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Laundry extends AppCompatActivity {

    ExpandableListAdapter listAdapter ;
    ExpandableListView expandableListView;
    List<String > list;
    private HashMap<String,List<String>> listHashMap;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    Button button;
    static final int DIALOG_ID = 0;
    int hour_x;
    int minute_x;
    Button button2;
    int year,month,day;int yearc,monthc,dayc;
    String txt;String txtc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);




        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("DELIVERY DETAILS");
        builder1.setIcon(R.drawable.applogo);


        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        builder1.setMessage("PICK UP DETAILS \n Time: "+ txtc + " Date:"  + dayc + " " + monthc + " " + yearc + "\n" + "Coming Soon.......");



        AlertDialog ad1 = builder1.create();


        button = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad1.show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                 year = calendar.get(Calendar.YEAR);
                 month = calendar.get(Calendar.MONTH);
                 day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Laundry.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                datePickerDialog.setTitle("SET THE DATE");



                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();


                hour_x = calendar.get(Calendar.HOUR_OF_DAY);
                minute_x = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Laundry.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {


                                Toast.makeText(Laundry.this,hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();



                            }
                        }, hour_x, minute_x, false);
                timePickerDialog.show();



            }
        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yearc = year;
                monthc = month;
                dayc = dayOfMonth;
            Toast.makeText(Laundry.this,dayOfMonth+" "+month+" "+year,Toast.LENGTH_SHORT).show();
            }
        };


        expandableListView = findViewById(R.id.lvExp);
        prepareListData ();
        listAdapter = new ExpandableListAdapter(this,list,listHashMap);
        expandableListView.setAdapter(listAdapter);

    }





    private void prepareListData() {
        list = new ArrayList<>();
        listHashMap = new HashMap<>();


        list.add("Wash and Fold");
        list.add("Wash and Iron");
        list.add("Express Delivery");
        list.add("Dry Clean");
        list.add("Price for per kg clothes");
        list.add("Prepaid Monthly Packages");
        list.add("Delivery Chargers");

        List <String> notification1 = new ArrayList<>();
        notification1.add("T-Shirt/Top            Rs.5");
        notification1.add("Shirt/Skirt            Rs.7");
        notification1.add("Jeans                  Rs.8");
        notification1.add("Pants/Trousers         Rs.7");
        notification1.add("Shorts                Rs.5");
        notification1.add("Kurti/Kurta            Rs.7");
        notification1.add("Hankerchief            Rs.3");
        notification1.add("Towel                  Rs.8");
        notification1.add("Blazer                Rs.20");
        notification1.add("Jackets/Sweater       Rs.17");
        notification1.add("BedSheet (Single)     Rs.10");
        notification1.add("BedSheet (Double)     Rs.15");
        notification1.add("Pillow Cover           Rs.4");
        notification1.add("Saree                 Rs.13");

        List <String> notifictaion2 = new ArrayList<>();
        notifictaion2.add("T-Shirt/Top            Rs.8");
        notifictaion2.add("Shirt/Skirt           Rs.10");
        notifictaion2.add("Jeans                 Rs.12");
        notifictaion2.add("Pants/Trousers        Rs.10");
        notifictaion2.add("Shorts                 Rs.8");
        notifictaion2.add("Kurti/Kurta           Rs.10");
        notifictaion2.add("Hankerchief            Rs.6");
        notifictaion2.add("Towel                 Rs.10");
        notifictaion2.add("Blazer                Rs.30");
        notifictaion2.add("Jackets/Sweater       Rs.25");
        notifictaion2.add("BedSheet (Single)     Rs.15");
        notifictaion2.add("BedSheet (Double)     Rs.25");
        notifictaion2.add("Pillow Cover           Rs.8");
        notifictaion2.add("Saree                Rs.20");


        List <String> notification3 = new ArrayList<>();
        notification3.add("T-Shirt/Top           Rs.7");
        notification3.add("Shirt/Skirt           Rs.9");
        notification3.add("Jeans                Rs.11");
        notification3.add("Pants/Trousers        Rs.9");
        notification3.add("Shorts                Rs.8");
        notification3.add("Kurti/Kurta          Rs.10");
        notification3.add("Hankerchief           Rs.6");
        notification3.add("Towel                Rs.12");
        notification3.add("Blazer               Rs.30");
        notification3.add("Jackets/Sweater      Rs.25");
        notification3.add("BedSheet (Single)    Rs.15");
        notification3.add("BedSheet (Double)    Rs.22");
        notification3.add("Pillow Cover         Rs.12");
        notification3.add("Saree                Rs.30");


        List <String> notification4 = new ArrayList<>();

        notification4.add("Trousers             Rs.50");
        notification4.add("Suit (2 piece)       Rs.12");
        notification4.add("Suit (2 piece)       Rs.12");
        notification4.add("Blazer               Rs.80");
        notification4.add("Jackets              Rs.70");
        notification4.add("Sweater              Rs.50");
        notification4.add("Saree                Rs.80");


        List <String> notification5 = new ArrayList<>();
        notification5.add("Wash and fold        Rs.50");
        notification5.add("Wash and Iron       Rs.100");
        notification5.add("Dry Clean           Rs.150");
        notification5.add("Express Delivery    Rs.200");


        List <String> notification7 = new ArrayList<>();
        notification7.add("0-2 km               Free");
        notification7.add("2-10 km              Rs.40");
        notification7.add("10-20 km             Rs.150");
        notification7.add("20 km and above      Rs.200");


        List <String> notification6 = new ArrayList<>();
        notification6.add("20 kg / month          Rs.1000");
        notification6.add("40 kg / month          Rs.1500");
        notification6.add("60 kg / month           Rs.150");




        listHashMap.put(list.get(0),notification1);
        listHashMap.put(list.get(1),notifictaion2);
        listHashMap.put(list.get(2),notification3);
        listHashMap.put(list.get(3),notification4);
        listHashMap.put(list.get(4),notification5);
        listHashMap.put(list.get(5),notification6);
        listHashMap.put(list.get(6),notification7);



    }


}
