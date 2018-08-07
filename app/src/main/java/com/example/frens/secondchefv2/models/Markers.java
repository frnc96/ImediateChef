package com.example.frens.secondchefv2.models;

public class Markers {

    private float latitude;
    private float longitude;
    private String locationTitle;
    private String locationImgUrl;

    public Markers(float latitude, float longitude, String locationTitle, String locationImgUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
        this.locationImgUrl = locationImgUrl;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationImgUrl() {
        return "drawable/"+locationImgUrl;
    }
}
