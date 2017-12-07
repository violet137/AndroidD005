package com.greenacademy.ga_finalprojecthm.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by GIT on 11/5/2017.
 */

public class LoaiHoTroAsyncTask extends AsyncTask<Void, Void, String> {
    Context context;
    IReceiverJSON iReceiverJSON;
    public void setiLoaiHoTro(IReceiverJSON iReceiverJSON){
        this.iReceiverJSON = iReceiverJSON;
    }
    public LoaiHoTroAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... area) {
        try {
            URL url = new URL("http://103.237.147.137:8050/api/HoTro/LoaiHoTro");
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
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "server error";
    }

    @Override
    protected void onPostExecute(String hotro) {
        iReceiverJSON.getStringJSON(hotro);
    }
}
