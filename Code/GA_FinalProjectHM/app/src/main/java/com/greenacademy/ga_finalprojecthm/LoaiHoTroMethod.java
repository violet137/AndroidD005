package com.greenacademy.ga_finalprojecthm;

import com.greenacademy.ga_finalprojecthm.model.LoaiHoTroJson;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTroJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GIT on 11/5/2017.
 */

public class LoaiHoTroMethod {
    public static RootLoaiHoTroJson parsetoLoaiHoTro(String strLoaiHoTro){
        RootLoaiHoTroJson rootLoaiHoTroJson = null;
        try {
            JSONObject rooJSON = new JSONObject(strLoaiHoTro);
            rootLoaiHoTroJson = new RootLoaiHoTroJson();
            int status = rooJSON.getInt("Status");
            String description = rooJSON.getString("Description");
            rootLoaiHoTroJson.setStatus(status);
            rootLoaiHoTroJson.setDescription(description);
            JSONArray loaihotro = rooJSON.getJSONArray("LoaiHoTroTranfers");
            for (int i =0; i<loaihotro.length(); i++){
                JSONObject hoTroJson = loaihotro.getJSONObject(i);
                int id = hoTroJson.getInt("IdHoTro");
                String tenloaihotro = hoTroJson.getString("TenHoTro");
                LoaiHoTroJson loaiHoTroJson = new LoaiHoTroJson();
                loaiHoTroJson.setIdHoTro(id);
                loaiHoTroJson.setTenHoTro(tenloaihotro);
                rootLoaiHoTroJson.getLoaiHoTroTranfers().add(loaiHoTroJson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootLoaiHoTroJson;
    }
}
