package com.greenacademy.ga_finalprojecthm.model.fashionset;

import java.util.ArrayList;

/**
 * Created by xuanson on 12/5/2017.
 */

public class FashionSet {
    ArrayList<SetDoTranfer> setDoTranferArrayList = new ArrayList<>();
    int idXuHuong;
    String hinh;
    String loaithoitrang;

    public ArrayList<SetDoTranfer> getSetDoTranferArrayList() {
        return setDoTranferArrayList;
    }

    public void setSetDoTranferArrayList(ArrayList<SetDoTranfer> setDoTranferArrayList) {
        this.setDoTranferArrayList = setDoTranferArrayList;
    }

    public int getIdXuHuong() {
        return idXuHuong;
    }

    public void setIdXuHuong(int idXuHuong) {
        this.idXuHuong = idXuHuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getLoaithoitrang() {
        return loaithoitrang;
    }

    public void setLoaithoitrang(String loaithoitrang) {
        this.loaithoitrang = loaithoitrang;
    }
}

