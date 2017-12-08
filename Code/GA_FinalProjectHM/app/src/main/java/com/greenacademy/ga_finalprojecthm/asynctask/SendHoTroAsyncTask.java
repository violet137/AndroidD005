package com.greenacademy.ga_finalprojecthm.asynctask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by GIT on 11/7/2017.
 */

public class SendHoTroAsyncTask extends AsyncTask<String, Void, String> {
    Context context;
    @Override
    protected String doInBackground(String... sendhotro) {
        try {
            URL url = new URL("http://103.237.147.137:8050/api/HoTro/GuiHoTro");
            //tạo connect lên server qua hàm openConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config giao thức truyền lên server
            connection.setRequestMethod("POST");
            //config server trả về data liểu xml
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept","application/json");
            connection.connect();
            JSONObject jsonObjectSendHoTro = new JSONObject();
            jsonObjectSendHoTro.put("LoaiHoTro", sendhotro[0]);
            jsonObjectSendHoTro.put("NoiDungHoTro", sendhotro[1]);
            jsonObjectSendHoTro.put("Email", sendhotro[2]);
            jsonObjectSendHoTro.put("Ten", sendhotro[3]);
            jsonObjectSendHoTro.put("Phone", " ");
            OutputStream outputStream = connection.getOutputStream();
            //pass data lên server
            String data = jsonObjectSendHoTro.toString();
            outputStream.write(data.getBytes("UTF-8"));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "server error";
    }

    @Override
    protected void onPostExecute(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s);
        builder.setCancelable(true);//ấn ra ngoài dailog sẽ tự biến mất thông qua thuộc tính setCancelable
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
