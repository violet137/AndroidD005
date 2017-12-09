package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by GIT on 11/16/2017.
 */

public class ChiTietSanPham {
    private int Id;
    private String ten;
    private int ngaytao;
    private int giatien;
    private int giatiengia;
    private String mota;
    private String chitiet;
    private ArrayList<LinkHinhChiTietSanPham> LinkHinh = new ArrayList<>();
    private ArrayList<String> MauSac = new ArrayList<>();
    private ArrayList<String> Size = new ArrayList<>();
    private ArrayList<String> SpPhuHop = new ArrayList<>();
    private int danhmuchangId;
    private String loaithoitrang;

    class LinkHinhChiTietSanPham{
        ArrayList<String> LinkHinhAnhCuaTungSanPham = new ArrayList<>();
        String mausacCuaTungSanPham;

        public ArrayList<String> getLinkHinhAnhCuaTungSanPham() {
            return LinkHinhAnhCuaTungSanPham;
        }

        public void setLinkHinhAnhCuaTungSanPham(ArrayList<String> linkHinhAnhCuaTungSanPham) {
            LinkHinhAnhCuaTungSanPham = linkHinhAnhCuaTungSanPham;
        }

        public String getMausacCuaTungSanPham() {
            return mausacCuaTungSanPham;
        }

        public void setMausacCuaTungSanPham(String mausacCuaTungSanPham) {
            this.mausacCuaTungSanPham = mausacCuaTungSanPham;
        }
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(int ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public int getGiatiengia() {
        return giatiengia;
    }

    public void setGiatiengia(int giatiengia) {
        this.giatiengia = giatiengia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public ArrayList<LinkHinhChiTietSanPham> getLinkHinh() {
        return LinkHinh;
    }

    public void setLinkHinh(ArrayList<LinkHinhChiTietSanPham> linkHinh) {
        LinkHinh = linkHinh;
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

    public ArrayList<String> getSpPhuHop() {
        return SpPhuHop;
    }

    public void setSpPhuHop(ArrayList<String> spPhuHop) {
        SpPhuHop = spPhuHop;
    }

    public int getDanhmuchangId() {
        return danhmuchangId;
    }

    public void setDanhmuchangId(int danhmuchangId) {
        this.danhmuchangId = danhmuchangId;
    }

    public String getLoaithoitrang() {
        return loaithoitrang;
    }

    public void setLoaithoitrang(String loaithoitrang) {
        this.loaithoitrang = loaithoitrang;
    }
}
