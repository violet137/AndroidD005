package com.greenacademy.ga_finalprojecthm.model.fashionset;

import java.util.ArrayList;

/**
 * Created by xuanson on 12/5/2017.
 */

public class FashionSetList {
    ArrayList<FashionSet> XuHuongTrangTranfers = new ArrayList<>();
    int status;
    String Description;

    public ArrayList<FashionSet> getXuHuongTrangTranfers() {
        return XuHuongTrangTranfers;
    }

    public void setXuHuongTrangTranfers(ArrayList<FashionSet> xuHuongTrangTranfers) {
        XuHuongTrangTranfers = xuHuongTrangTranfers;
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
