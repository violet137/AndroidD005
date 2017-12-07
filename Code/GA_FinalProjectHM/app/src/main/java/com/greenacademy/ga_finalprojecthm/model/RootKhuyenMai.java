package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by HoangHai Nguyen on 11/15/2017.
 */

public class RootKhuyenMai {
    private ArrayList<KhuyenMaiJson> KhuyenMaiTranfers = new ArrayList<>();
    private int Status;
    private String Description;

    public ArrayList<KhuyenMaiJson> getKhuyenMaiTranfers() {
        return KhuyenMaiTranfers;
    }

    public void setKhuyenMaiTranfers(ArrayList<KhuyenMaiJson> khuyenMaiTranfers) {
        KhuyenMaiTranfers = khuyenMaiTranfers;
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
