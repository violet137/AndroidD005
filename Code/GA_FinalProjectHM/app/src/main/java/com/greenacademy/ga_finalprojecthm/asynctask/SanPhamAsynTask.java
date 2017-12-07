package com.greenacademy.ga_finalprojecthm.asynctask;

import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHang;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHangTranfers;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucList;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hoang Tin on 14-Nov-17.
 */

public class SanPhamAsynTask extends AsyncTask<String, Void, String> {



    private IReceiverJSON ReceiverJSON;

    public void iCallBack(IReceiverJSON iReceiverJSON){
        this.ReceiverJSON=iReceiverJSON;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=  new URL( "http://35.227.90.131:9001/api/Home/DanhMucHang?loaiThoiTrang="+strings[0]);
            try {
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.addRequestProperty("Accept", "text/json");
                //config giao thức truyềnhttp://103.237.147.137:8050/api lên server
                connection.setRequestMethod("GET");
                connection.connect();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        total.append(line).append("\n");
                    }
                    return total.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        return "Sever Loi";
    }

    @Override
    protected void onPostExecute(String s) {
        this.ReceiverJSON.getStringJSON(s);
    }
}
//van khong set dc du lieu de em debug lai