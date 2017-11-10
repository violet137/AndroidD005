package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by hiang on 11/2/2017.
 */

public class RootTapChi {
    private ArrayList<TapChiJson> tapChiTranfers=new ArrayList<>();
    private int status;
    private String description;

    public ArrayList<TapChiJson> getTapChiTranfers() {
        return tapChiTranfers;
    }

    public void setTapChiTranfers(ArrayList<TapChiJson> tapChiTranfers) {
        this.tapChiTranfers = tapChiTranfers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
