package com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham;

import java.util.ArrayList;

/**
 * Created by Hoang Tin on 04-Dec-17.
 */

public class ChiTietSanPham {
    ArrayList<SanPhamTranfers> sanPhamTranfers=new ArrayList<>();
    int Status;
    String Description;

    public ArrayList<SanPhamTranfers> getSanPhamTranfers() {
        return sanPhamTranfers;
    }

    public void setSanPhamTranfers(ArrayList<SanPhamTranfers> sanPhamTranfers) {
        this.sanPhamTranfers = sanPhamTranfers;
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
