package com.greenacademy.ga_finalprojecthm.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.ICallBack;
import com.greenacademy.ga_finalprojecthm.model.RootTapChi;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hiang on 11/2/2017.
 */

public class AsynTaskTapChi extends AsyncTask<String , Void , String> {

    private ICallBack iCallBack;

    public void ICallBack(ICallBack iCallBack){
        this.iCallBack = iCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://35.227.90.131:9001/api/TapChi/TapChi?loaiTapChi="+strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config server trả về kiểu xml
            connection.addRequestProperty("Accept", "text/json");
            //config giao thức truyền lên server
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    total.append(line).append("\n");
                }
                return total.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Server Error!";
    }

    @Override
    protected void onPostExecute(String TapChiOnPost) {
        super.onPostExecute(TapChiOnPost);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(TapChiOnPost);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        try {
            JSONObject rootJson = new JSONObject(TapChiOnPost);
            RootTapChi root_TapChi = new RootTapChi();

            int status = rootJson.getInt("Status");
            String description = rootJson.getString("Description");

            root_TapChi.setStatus(status);
            root_TapChi.setDescription(description);

            JSONArray ListTapChi = rootJson.getJSONArray("TapChiTranfers");
            for(int i = 0; i < ListTapChi.length(); i++){
                JSONObject JsonTapChi = ListTapChi.getJSONObject(i);
                int IdTapChi = JsonTapChi.getInt("IdTapChi");
                String LoaiTapChi = JsonTapChi.getString("LoaiTapChi");
                String Ten = JsonTapChi.getString("Ten");
                String MoTa = JsonTapChi.getString("MoTa");
                String LinkHinh = JsonTapChi.getString("LinkHinh");

                TapChiJson tapChiJson = new TapChiJson();
                tapChiJson.setIdTapChi(IdTapChi);
                tapChiJson.setLoaiTapChi(LoaiTapChi);
                tapChiJson.setTen(Ten);
                tapChiJson.setMoTa(MoTa);
                tapChiJson.setLinkHinh(LinkHinh);

                root_TapChi.getTapChiTranfers().add(tapChiJson);
            }
            iCallBack.GetTapChi(root_TapChi);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
