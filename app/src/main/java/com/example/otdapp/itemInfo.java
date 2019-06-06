package com.example.otdapp;

public class itemInfo {

    public String name;
    public String price;

    public itemInfo() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public itemInfo(String name,String price) {

        this.price = price;
        this.name = name;

    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
