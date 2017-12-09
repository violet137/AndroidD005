package com.greenacademy.ga_finalprojecthm.model.fashionset;

import com.greenacademy.ga_finalprojecthm.model.fashionset.SanPhamTranfer;

import java.util.ArrayList;

public class SetDoTranfer {
    ArrayList<SanPhamTranfer> sanPhamTranferArrayList = new ArrayList<>();
    int idSet;
    String hinhSet;

    public ArrayList<SanPhamTranfer> getSanPhamTranferArrayList() {
        return sanPhamTranferArrayList;
    }

    public void setSanPhamTranferArrayList(ArrayList<SanPhamTranfer> sanPhamTranferArrayList) {
        this.sanPhamTranferArrayList = sanPhamTranferArrayList;
    }

    public int getIdSet() {
        return idSet;
    }

    public void setIdSet(int idSet) {
        this.idSet = idSet;
    }

    public String getHinhSet() {
        return hinhSet;
    }

    public void setHinhSet(String hinhSet) {
        this.hinhSet = hinhSet;
    }
}
