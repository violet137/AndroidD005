package com.greenacademy.ga_finalprojecthm.model;

/**
 * Created by xuanson on 11/6/2017.
 */

public class FashionShop {
    String name,address,style;
    Double lat,lng,evaluate;

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
}
