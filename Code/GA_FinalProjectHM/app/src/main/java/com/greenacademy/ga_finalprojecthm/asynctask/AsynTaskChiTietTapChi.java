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
 * Created by hiang on 11/2/2017.
 */

public class AsynTaskChiTietTapChi extends AsyncTask<Integer, Void, String> {

    private IReceiverJSON iReceiverJSON;

    public void setiReceiverJSON(IReceiverJSON iReceiverJSON) {
        this.iReceiverJSON = iReceiverJSON;
    }


    @Override
    protected String doInBackground(Integer... data) {
        try {
            URL url = new URL("http://35.227.90.131:9001/api/TapChi/ChiTietTapChi?idTapChi=" + data[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config server trả về kiểu xml
            connection.addRequestProperty("Accept", "text/json");
            //config giao thức truyền lên server
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    total.append(line).append("\n");
                }
                return total.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Server Lỗi";
    }

    @Override
    protected void onPostExecute(String chiTietTapChiOnPost) {
        super.onPostExecute(chiTietTapChiOnPost);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(ChiTietTapChiOnPost);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

        iReceiverJSON.getStringJSON(chiTietTapChiOnPost);
    }
}
