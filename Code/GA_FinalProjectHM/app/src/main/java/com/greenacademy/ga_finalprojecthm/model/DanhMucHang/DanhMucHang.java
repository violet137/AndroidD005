package com.greenacademy.ga_finalprojecthm.model.DanhMucHang;

/**
 * Created by Hoang Tin on 12-Nov-17.
 */

public class DanhMucHang {
    private DanhMucHangTranfers danhMucHangTranfers = new DanhMucHangTranfers();
    private int Status;
    private String Description;

    public DanhMucHangTranfers getDanhMucHangTranfers() {
        return danhMucHangTranfers;
    }

    public void setDanhMucHangTranfers(DanhMucHangTranfers danhMucHangTranfers) {
        this.danhMucHangTranfers = danhMucHangTranfers;
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
