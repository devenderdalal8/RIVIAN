package com.example.prototype.ui.home;

public class ChargerModel {
    String address;
    String available;
    String distance;

    public ChargerModel(String address, String available, String distance) {
        this.address = address;
        this.available = available;
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}