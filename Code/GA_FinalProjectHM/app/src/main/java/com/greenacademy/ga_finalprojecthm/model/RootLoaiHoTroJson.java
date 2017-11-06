package com.greenacademy.ga_finalprojecthm.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by GIT on 11/5/2017.
 */

public class RootLoaiHoTroJson {
    ArrayList<LoaiHoTroJson> LoaiHoTroTranfers = new ArrayList<>();
    int status;
    String Description;

    public ArrayList<LoaiHoTroJson> getLoaiHoTroTranfers() {
        return LoaiHoTroTranfers;
    }

    public void setLoaiHoTroTranfers(ArrayList<LoaiHoTroJson> loaiHoTroTranfers) {
        LoaiHoTroTranfers = loaiHoTroTranfers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
