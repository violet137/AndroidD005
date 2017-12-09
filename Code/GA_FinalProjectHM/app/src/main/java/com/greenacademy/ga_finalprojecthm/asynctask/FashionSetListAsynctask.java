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
 * Created by xuanson on 12/6/2017.
 */

public class FashionSetListAsynctask extends AsyncTask<String, Void, String> {
    private IReceiverJSON iReceiverJSON;

    public void iCallBack(IReceiverJSON iReceiverJSON){
        this.iReceiverJSON = iReceiverJSON;
    }

    @Override
    protected String doInBackground(String... param) {
        try {
            URL url = new URL("http://35.227.90.131:9001/api/XuHuongTtrang/XhuongTheoThoiTrang?loaiThoiTrang="
                    +param[0]+"&soLuongLay=10");
            //tạo connect lên server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config server trả về data dang json
            connection.addRequestProperty("Accept", "text/json");
            //config giao thức truyền lên server
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //hàm cộng dồn chuổi
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                //đọc data liên tiếp cho ts khi null thì dừng lại
                while ((line = bufferedReader.readLine())!= null){
                    stringBuilder.append(line).append('\n');
                }
                return stringBuilder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "server fault";
    }

    @Override
    protected void onPostExecute(String setdo) {
        iReceiverJSON.getStringJSON(setdo);
    }
}
