package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by GIT on 11/5/2017.
 */

public class RootLoaiHoTro {
    ArrayList<LoaiHoTro> LoaiHoTroTranfers = new ArrayList<>();
    int status;
    String Description;

    public ArrayList<LoaiHoTro> getLoaiHoTroTranfers() {
        return LoaiHoTroTranfers;
    }

    public void setLoaiHoTroTranfers(ArrayList<LoaiHoTro> loaiHoTroTranfers) {
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
