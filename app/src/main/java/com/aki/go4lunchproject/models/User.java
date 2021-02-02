package com.aki.go4lunchproject.models;

import androidx.annotation.Nullable;

public class User {

    private String uid, username;
    private Boolean hasBooked;
    @Nullable private String urlPicture;

    public User() {}

    public User(String uid, String username, @Nullable String urlPicture) {
        this.uid = uid;
        this.urlPicture = urlPicture;
        this.username = username;
        this.hasBooked = false;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getHasBooked() {
        return hasBooked;
    }

    public void setHasBooked(Boolean hasBooked) {
        this.hasBooked = hasBooked;
    }

    @Nullable
    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(@Nullable String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
