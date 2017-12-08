package com.greenacademy.ga_finalprojecthm.asynctask;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by thepa on 11/16/2017.
 */

public class HomeASyncTask extends AsyncTask<String, Void, String> {

    private IReceiverJSON iReceiverJSON;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL( strings[0] + "Home/" + strings[1]);
            //create connection to server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //config server return data type xml
            //config which data receive from server
            conn.addRequestProperty("Accept", "text/json");
            //config which data sent to server
//            conn.addRequestProperty("Content-Type", "application/json");
            //config protocol on server
            conn.setRequestMethod("GET");

//            OutputStream outputStream = conn.getOutputStream();
//            outputStream.write(strings[0].getBytes());

            conn.connect();
            
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    total.append(line).append("\n");
                }
                return total.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Server error!";
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
