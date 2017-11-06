package com.greenacademy.ga_finalprojecthm;

import com.greenacademy.ga_finalprojecthm.model.LoaiHoTroJson;
import com.greenacademy.ga_finalprojecthm.model.QuestionJSON;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTroJson;
import com.greenacademy.ga_finalprojecthm.model.RootSupportJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GIT on 11/4/2017.
 */

public class SupportMethod {
    public static RootSupportJSON parsetoQuestion(String support){
        RootSupportJSON rootSupport = null;
        try {
            JSONObject rootJSON = new JSONObject(support);
            rootSupport = new RootSupportJSON();
            int status = rootJSON.getInt("Status");
            String description = rootJSON.getString("Description");
            rootSupport.setStatus(status);
            rootSupport.setDescription(description);
            JSONArray bocauhoi = rootJSON.getJSONArray("CauHoiTranfers");
            for (int i =0; i<bocauhoi.length(); i++){
                JSONObject cauhoiJSON = bocauhoi.getJSONObject(i);
                int id = cauhoiJSON.getInt("Id");
                String noidungQuestion = cauhoiJSON.getString("NoiDungCauHoi");
                String traloi = cauhoiJSON.getString("TraLoi");
                String html = cauhoiJSON.getString("Html");
                QuestionJSON cauhoi = new QuestionJSON();
                cauhoi.setId(id);
                cauhoi.setNoiDungCauHoi(noidungQuestion);
                cauhoi.setTraLoi(traloi);
                cauhoi.setHtml(html);
                rootSupport.getCauHoiTranfers().add(cauhoi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootSupport;
    }

}
