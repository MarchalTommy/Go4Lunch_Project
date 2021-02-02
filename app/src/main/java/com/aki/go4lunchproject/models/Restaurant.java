package com.aki.go4lunchproject.models;

public class Restaurant {

    private String uid, name, address, imageUrl;
    private Boolean isBooked;

    public Restaurant() {}

    public Restaurant(String uid, String name, String address, String imageUrl) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isBooked = false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getBookedState() {
        return isBooked;
    }

    public void setBookedState(Boolean booked) {
        isBooked = booked;
    }
}
