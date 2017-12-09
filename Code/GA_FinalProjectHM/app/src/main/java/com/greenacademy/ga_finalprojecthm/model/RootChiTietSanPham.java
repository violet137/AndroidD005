package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by GIT on 11/16/2017.
 */

public class RootChiTietSanPham {
    private ArrayList<ChiTietSanPham> SanPhamTranfers = new ArrayList<>();
    private int status;
    private String Description;

    public ArrayList<ChiTietSanPham> getSanPhamTranfers() {
        return SanPhamTranfers;
    }

    public void setSanPhamTranfers(ArrayList<ChiTietSanPham> sanPhamTranfers) {
        SanPhamTranfers = sanPhamTranfers;
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
