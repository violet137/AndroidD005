package com.greenacademy.ga_finalprojecthm.model.fashionshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by xuanson on 11/6/2017.
 */

public class FashionShop {
    String hours;
    String name, address, style;
    Double lat, lng, evaluate;
    LatLng currentLocation;

    public Double getDistance(Context context,double latA, double lngA) {
        double distance;
        Location locationA = new Location("Point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB =new Location("Point B");
        //load share preference
        SharedPreferences preferences = context.getSharedPreferences("myLocation",Context.MODE_PRIVATE);
        locationB.setLatitude(10.773127);
        locationB.setLongitude(106.689047);
//        locationB.setLatitude(Double.parseDouble(preferences.getString("currentLocation", String.valueOf(latitude))));
//        locationB.setLongitude(Double.parseDouble(preferences.getString("currentLocation", String.valueOf(longitude))));

//        distance = locationA.distanceTo(locationB);   // in meters
        distance = locationA.distanceTo(locationB) / 1000; // in km
        return distance;
    }

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {
        this.currentLocation = currentLocation;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Double evaluate) {
        this.evaluate = evaluate;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
