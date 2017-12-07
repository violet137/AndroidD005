package com.greenacademy.ga_finalprojecthm.model.DanhMucHang;

import java.util.ArrayList;

/**
 * Created by Hoang Tin on 12-Nov-17.
 */

public class DanhMucHangTranfers {
    private ArrayList<DanhMucList> danhMucLists= new ArrayList<>();
    private String LoaiThoiTrang;
    private int XuHuongTtrangId;
    private String XuHuongTtrangLink;

    public ArrayList<DanhMucList> getDanhMucLists() {
        return danhMucLists;
    }

    public void setDanhMucLists(ArrayList<DanhMucList> danhMucLists) {
        this.danhMucLists = danhMucLists;
    }

    public String getLoaiThoiTrang() {
        return LoaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        LoaiThoiTrang = loaiThoiTrang;
    }

    public int getXuHuongTtrangId() {
        return XuHuongTtrangId;
    }

    public void setXuHuongTtrangId(int xuHuongTtrangId) {
        XuHuongTtrangId = xuHuongTtrangId;
    }

    public String getXuHuongTtrangLink() {
        return XuHuongTtrangLink;
    }

    public void setXuHuongTtrangLink(String xuHuongTtrangLink) {
        XuHuongTtrangLink = xuHuongTtrangLink;
    }
}
