package com.example.farmerbuddy;

public class User {
    String name;
    String phone_number;
    String address;
    String city;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String name, String phone_number, String address, String city) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
