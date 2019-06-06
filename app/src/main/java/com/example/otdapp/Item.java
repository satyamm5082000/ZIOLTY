package com.example.otdapp;

public class Item {

    private String name ;
    private String price;

    public Item (String name,String price)
    {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
