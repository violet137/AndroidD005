package com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham;

import java.util.ArrayList;

/**
 * Created by Hoang Tin on 04-Dec-17.
 */

public class HinhByColor {
    private ArrayList<String> LinkHinh=new ArrayList<>();
    String MauSac;

    public ArrayList<String> getLinkHinh() {
        return LinkHinh;
    }

    public void setLinkHinh(ArrayList<String> linkHinh) {
        LinkHinh = linkHinh;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String mauSac) {
        MauSac = mauSac;
    }
}
