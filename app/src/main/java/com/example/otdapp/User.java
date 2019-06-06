package com.example.otdapp;

public class User {

    public String name;
    public String email;
    public String mobile_no;
    public String address;



    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name,String mobile_no,String email ,String address) {

        this.email = email;
        this.name = name;
        this.mobile_no = mobile_no;
        this.address = address;


    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setName(String name) {
        this.name = name;
    }
}
