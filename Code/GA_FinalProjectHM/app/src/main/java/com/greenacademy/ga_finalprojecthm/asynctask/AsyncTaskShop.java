package com.greenacademy.ga_finalprojecthm.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.util.OnReceiverShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by GIT on 9/21/2017.
 */

public class AsyncTaskShop extends AsyncTask<Object, Object, String> {
    Context context;
    OnReceiverShop onReceiverShop;

    public void iCallBack(OnReceiverShop onReceiverShop){
        this.onReceiverShop = onReceiverShop;
    }

    public AsyncTaskShop(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... voids) {
        try {
            URL url = new URL("http://tamod.vn:8050/api/CuaHang/DanhSachCuaHang");
            //tạo connect lên server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config server trả về data dang json
            connection.addRequestProperty("Accept", "text/json");
            //config giao thức truyền lên erver
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //hàm cộng dồn chuổi
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                //đọc data liên tiếp cho ts khi null thì dừng lại
                while ((line = bufferedReader.readLine())!= null){
                    stringBuffer.append(line).append('\n');
                }
                return stringBuffer.toString();
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
        onReceiverShop.getShop̣̣(thoitrang);
    }
}
