package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by HoangHai Nguyen on 11/17/2017.
 */

public class RootChiTietKhuyenMai {
    private ArrayList<ChiTietKhuyenMaiJSON> listSP = new ArrayList<>();

    public ArrayList<ChiTietKhuyenMaiJSON> getListSP() {
        return listSP;
    }

    public void setListSP(ArrayList<ChiTietKhuyenMaiJSON> listSP) {
        this.listSP = listSP;
    }

    private int id;
    private String TenCTKM;
    private String HinhCTKM;
    private String MoTaCTKM;
    private int Status;
    private String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCTKM() {
        return TenCTKM;
    }

    public void setTenCTKM(String tenCTKM) {
        TenCTKM = tenCTKM;
    }

    public String getHinhCTKM() {
        return HinhCTKM;
    }

    public void setHinhCTKM(String hinhCTKM) {
        HinhCTKM = hinhCTKM;
    }

    public String getMoTaCTKM() {
        return MoTaCTKM;
    }

    public void setMoTaCTKM(String moTaCTKM) {
        MoTaCTKM = moTaCTKM;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

