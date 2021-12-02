package services;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ServiceApi {
    private final static String url = "https://api-treinela.herokuapp.com/";


    public static String getService(String dataSet, String method, String data, String token) {
        String reqUrl = url + dataSet;
        if (method == "GET") {
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(token!=""){
                    conn.setRequestProperty("Authorization", "Bearer " + token);
                }
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestMethod(method);
                int responseCode = conn.getResponseCode();

                if(responseCode == HttpsURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    return convertStreamToString(in);
                }else if(responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED){
                    return "401";
                }
                return "Erro";
            } catch (Exception e) {
                Log.e("Service Api", "Exception: " + e.getMessage());
            }
            return response;
        }

        else if(method == "POST") {
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                if(token!=""){
                    conn.setRequestProperty("Authorization","Bearer " + token);
                }
                conn.setRequestMethod(method);
                OutputStream out = conn.getOutputStream();
                byte[] input = data.getBytes("utf-8");
                out.write(input, 0, input.length);
                int responseCode= conn.getResponseCode();
                if(responseCode == HttpsURLConnection.HTTP_CREATED){
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    return convertStreamToString(in);
                }else if(responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED){
                    return "401";
                }
                return "Erro";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (method == "DELETE") {
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(token!=""){
                    conn.setRequestProperty("Authorization","Bearer " + token);
                }
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestMethod(method);
                InputStream in = new BufferedInputStream(conn.getInputStream());
                return "OK";
            } catch (Exception e) {
                Log.e("Service Api", "Exception: " + e.getMessage());
            }
        }
        else if(method == "PUT") {
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                if(token!=""){
                    conn.setRequestProperty("Authorization","Bearer " + token);
                }
                conn.setRequestMethod(method);
                OutputStream out = conn.getOutputStream();
                byte[] input = data.getBytes("utf-8");
                out.write(input, 0, input.length);
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK)
                    return "OK";
                return "Erro";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}


