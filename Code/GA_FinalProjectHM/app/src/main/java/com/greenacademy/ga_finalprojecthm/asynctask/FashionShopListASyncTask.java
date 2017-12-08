package com.greenacademy.ga_finalprojecthm.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

/**
 * Created by GIT on 9/21/2017.
 */

public class FashionShopListASyncTask extends AsyncTask<Void, Void, String> {

    private IReceiverJSON iReceiverJSON;

    public void iCallBack(IReceiverJSON iReceiverJSON){
        this.iReceiverJSON = iReceiverJSON;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("http://103.237.147.137:8050/api/CuaHang/DanhSachCuaHang");
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
    protected void onPostExecute(String thoitrang) {
        iReceiverJSON.getStringJSON(thoitrang);
    }
}
