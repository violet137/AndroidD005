package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiang on 10/31/2017.
 */

public class RootLoaiTapChi {
    private ArrayList<LoaiTapChiJson> LoaiTapChiTranfers = new ArrayList<>();
    private int Status;
    private String Description;

    public ArrayList<LoaiTapChiJson> getLoaiTapChiTranfers() {
        return LoaiTapChiTranfers;
    }

    public void setLoaiTapChiTranfers(ArrayList<LoaiTapChiJson> loaiTapChiTranfers) {
        LoaiTapChiTranfers = loaiTapChiTranfers;
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
