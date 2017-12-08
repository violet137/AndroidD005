package com.greenacademy.ga_finalprojecthm.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hoang Tin on 11/7/2017.
 */

public class LoginAsyncTask extends AsyncTask<String, Void, String> {

    private IReceiverJSON iReceiverJSON;
    private Context context;

    public LoginAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://35.227.90.131:9001/api/Auth/Login");

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.addRequestProperty("Accept", "text/json");
                conn.addRequestProperty("Content-Type", "application/json");
                // giao  thuc truyen len server
                conn.setRequestMethod("POST");
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("Username", strings[0]);
                    jsonObject.put("Pwd", strings[1]);
                    jsonObject.put("AccountType", "Google");
                    jsonObject.put("NenTang", "Android");
                    String strJsonObject = jsonObject.toString();
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(strJsonObject.getBytes());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //String builder la ham cong don chuoi
                    StringBuilder total = new StringBuilder();
                    String Status = reader.readLine();
                    while (Status != null) {
                        total.append(Status);
                        Status = reader.readLine();
                    }

                    return total.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return context.getResources().getString(R.string.server_error);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iReceiverJSON.getStringJSON(s);
    }

    public void setiReceiverJSON(IReceiverJSON iReceiverJSON){
        this.iReceiverJSON = iReceiverJSON;
    }
}
