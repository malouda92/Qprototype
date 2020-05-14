package com.cnaps.ramanandafylovamialy.lib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceHTTP extends AsyncTask<String, Void, Integer> {

    private ProgressDialog pb = null;
    private int responseCode;
    private BufferedReader reader;
    public String responseServer;
    public Context context;
    public OnTaskCompleted listener;

    @Override
    protected void onPreExecute(){
        pb = new ProgressDialog(this.context);
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pb.setMessage("en cours");

    }

    @Override
    protected Integer doInBackground(String... param) {

        try{
            URL url = new URL(param[0]);
            HttpURLConnection connexion = (HttpURLConnection)url.openConnection();
            connexion.setRequestMethod("GET");
            responseCode = connexion.getResponseCode();
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            StringBuilder sb =new StringBuilder();
            String line = "";
            while((line = reader.readLine())!= null){
                sb.append(line);
            }
            responseServer = sb.toString();

        }catch(Exception e){
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    protected void onPostExecute(Integer code) {
        pb.hide();
        if(code == 200){
            this.listener.onTaskCompleted(responseServer);
        }
    }

}
