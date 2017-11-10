package com.greenacademy.ga_finalprojecthm.asynctask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hiang on 10/31/2017.
 */

public class ForgetPasswordASynctask extends AsyncTask<String, Void, String> {
    Context context;

    public ForgetPasswordASynctask(Context baseContext) {
        this.context = baseContext;
    }

    public ForgetPasswordASynctask() {

    }

    @Override
    protected String doInBackground(String... data) {
        try{
            URL url = new URL("" + data[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //config server trả về data kiểu xml
            connection.addRequestProperty("Accept", "text/xml");
            //config giao thức truyền lên server
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    total.append(line).append("\n");
                }
                return total.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Server lỗi";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(result);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
























