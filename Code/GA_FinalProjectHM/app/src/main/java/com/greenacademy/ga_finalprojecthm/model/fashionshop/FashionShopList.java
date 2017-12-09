package com.greenacademy.ga_finalprojecthm.model.fashionshop;

import com.greenacademy.ga_finalprojecthm.model.fashionshop.FashionShop;

import java.util.ArrayList;

/**
 * Created by GIT on 9/21/2017.
 */

public class FashionShopList {
    ArrayList<FashionShop> CuaHangTranfers = new ArrayList<>();
    int status;
    String Description;

    public ArrayList<FashionShop> getCuaHangTranfers() {
        return CuaHangTranfers;
    }

    public void setCuaHangTranfers(ArrayList<FashionShop> cuaHangTranfers) {
        CuaHangTranfers = cuaHangTranfers;
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
