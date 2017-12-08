package com.greenacademy.ga_finalprojecthm.model.DanhMucHang;

/**
 * Created by Hoang Tin on 12-Nov-17.
 */

public class DanhMucList {
    private int Id;
    private String TenDanhMuc;
    private String LoaiThoiTrang;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getLoaiThoiTrang() {
        return LoaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        LoaiThoiTrang = loaiThoiTrang;
    }
}
