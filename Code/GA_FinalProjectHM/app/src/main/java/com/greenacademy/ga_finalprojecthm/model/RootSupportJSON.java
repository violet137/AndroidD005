package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by GIT on 11/4/2017.
 */

public class RootSupportJSON {
    ArrayList<QuestionJSON> CauHoiTranfers = new ArrayList<>();
    int status;
    String Description;
    public ArrayList<QuestionJSON> getCauHoiTranfers() {
        return CauHoiTranfers;
    }

    public void setCauHoiTranfers(ArrayList<QuestionJSON> cuaHangTranfers) {
        CauHoiTranfers = cuaHangTranfers;
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
