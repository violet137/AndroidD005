package com.greenacademy.ga_finalprojecthm.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by xuanson on 11/6/2017.
 */

public class FashionShop {
    String hours;
    String name, address, style;
    Double lat, lng, evaluate;
    double latitude,longitude;
    Location mLastLocation;
    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;

    @SuppressLint("MissingPermission")
    public Location getMyCurrentLocation(Location mLastLocation,GoogleMap mGoogleMap,GoogleApiClient mGoogleApiClient) {
//        LatLng currentLocation = null;
        this.mLastLocation = mLastLocation;
        this.mGoogleApiClient = mGoogleApiClient;
        this.mGoogleMap = mGoogleMap;
        @SuppressLint("MissingPermission") LocationAvailability locationAvailability =
                LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
        if (null != locationAvailability && locationAvailability.isLocationAvailable()) {
            // 3
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            // 4
            if (mLastLocation != null) {
                LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                        .getLongitude());
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
            }
        }
        return mLastLocation;
    }

    public Double getDistance(double latA, double lngA) {
        double distance;
        Location locationA = new Location("Point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB =new Location("Point B");
        locationB.setLatitude(getMyCurrentLocation(mLastLocation,mGoogleMap,mGoogleApiClient).getLatitude());
        locationB.setLongitude(getMyCurrentLocation(mLastLocation,mGoogleMap,mGoogleApiClient).getLongitude());

//        distance = locationA.distanceTo(locationB);   // in meters
        distance = locationA.distanceTo(locationB) / 1000; // in km
        return distance;
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
