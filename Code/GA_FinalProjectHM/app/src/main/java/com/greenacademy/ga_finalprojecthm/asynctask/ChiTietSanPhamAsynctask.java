package com.greenacademy.ga_finalprojecthm.asynctask;

import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hoang Tin on 30-Nov-17.
 */

public class ChiTietSanPhamAsynctask extends AsyncTask<String, Void, String> {
    IReceiverJSON ReceiverJSON;

    public void iCallBack(IReceiverJSON iReceiverJSON) {
        this.ReceiverJSON = iReceiverJSON;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL("http://35.227.90.131:9001/api/SanPham/SanPhamTheoThoiTrang?loaiThoiTrang="+strings[0]);
            try {
                HttpURLConnection connection= (HttpURLConnection)url.openConnection();
                connection.addRequestProperty("Accept", "text/json");
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
        }
        return "server loi";
    }

    @Override
    protected void onPostExecute(String s) {
       ReceiverJSON.getStringJSON(s);
    }
}
