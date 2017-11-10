package com.greenacademy.ga_finalprojecthm.model;

/**
 * Created by hiang on 11/2/2017.
 */

public class TapChiJson {
    private int idTapChi;
    private String loaiTapChi;
    private String ten;
    private String moTa;
    private String linkHinh;
    private String noiDung;
    private int status;
    private String description;

    public int getIdTapChi() {
        return idTapChi;
    }

    public void setIdTapChi(int idTapChi) {
        this.idTapChi = idTapChi;
    }

    public String getLoaiTapChi() {
        return loaiTapChi;
    }

    public void setLoaiTapChi(String loaiTapChi) {
        this.loaiTapChi = loaiTapChi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}