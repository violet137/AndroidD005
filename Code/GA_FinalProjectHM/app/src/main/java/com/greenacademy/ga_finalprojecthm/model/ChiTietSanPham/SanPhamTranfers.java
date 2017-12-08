package com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham;

import java.util.ArrayList;

/**
 * Created by Hoang Tin on 04-Dec-17.
 */

public class SanPhamTranfers {
    int ID;
    String Ten;
    String Ngaytao;
    int GiaTien;
    int GiaTienGiam;
    String MoTa;
    String ChiTiet;
    ArrayList<HinhByColor> hinhByColors =new ArrayList<>();
    ArrayList<String> MauSac=new ArrayList<>();
    ArrayList<String> Size=new ArrayList<>();
    int[] SapPhamPhuHop;
    int DanhMucHangID;
    String LoaiThoiTrang;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        Ngaytao = ngaytao;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public int getGiaTienGiam() {
        return GiaTienGiam;
    }

    public void setGiaTienGiam(int giaTienGiam) {
        GiaTienGiam = giaTienGiam;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        ChiTiet = chiTiet;
    }

    public ArrayList<HinhByColor> getHinhByColors() {
        return hinhByColors;
    }

    public void setHinhByColors(ArrayList<HinhByColor> hinhByColors) {
        this.hinhByColors = hinhByColors;
    }

    public ArrayList<String> getMauSac() {
        return MauSac;
    }

    public void setMauSac(ArrayList<String> mauSac) {
        MauSac = mauSac;
    }

    public ArrayList<String> getSize() {
        return Size;
    }

    public void setSize(ArrayList<String> size) {
        Size = size;
    }

    public int[] getSapPhamPhuHop() {
        return SapPhamPhuHop;
    }

    public void setSapPhamPhuHop(int[] sapPhamPhuHop) {
        SapPhamPhuHop = sapPhamPhuHop;
    }

    public int getDanhMucHangID() {
        return DanhMucHangID;
    }

    public void setDanhMucHangID(int danhMucHangID) {
        DanhMucHangID = danhMucHangID;
    }

    public String getLoaiThoiTrang() {
        return LoaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        LoaiThoiTrang = loaiThoiTrang;
    }
}
