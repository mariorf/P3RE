package com.example.p3re.utils;

import android.net.Uri;
import android.util.Log;
import com.example.p3re.data.ShadowJ;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class ShadowsAPI {
    String BASE_URL = "https://kcpdykkspiuyjmwvyxqa.supabase.co/rest/v1";
    String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g";

    public ArrayList<ShadowJ> getShadows(){

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("apikey")
                .appendQueryParameter("apikey",API_KEY)
                .build();
        String url = builtUri.toString();
        Log.d("tuViejita",url);

        return doCall(url);
    }



    private ArrayList<ShadowJ> doCall(String url) {
        try {
            Log.d("tuViejita","Inicio doCall");
            String jsonResponse = HttpUtils.get(url);
            Log.d("nose",jsonResponse);
            return processJson(jsonResponse);
        } catch (Exception e) {
            Log.d("tuViejita","ERROR");
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<ShadowJ> processJson(String jsonResponse) {
        ArrayList<ShadowJ> users = new ArrayList<>();
        Log.d("tuViejita","Inicio processJson");

        try {
            JSONArray jsonShadows = new JSONArray(jsonResponse);
            Log.d("ANGALEMEN","JSON CREADO");
            for (int i = 0; i < jsonShadows.length(); i++){
                JSONObject shadowData = jsonShadows.getJSONObject(i);
                ShadowJ shadow = new ShadowJ();
                shadow.setKey(shadowData.getString("_key"));
                shadow.setName(shadowData.getString("name"));
                shadow.setArea(shadowData.getString("area"));
                shadow.setLvl(shadowData.getInt("lvl"));
                shadow.setResists(shadowData.getString("resists"));
                shadow.setStats0(shadowData.getInt("stats/0"));
                shadow.setStats1(shadowData.getInt("stats/1"));
                shadow.setStats2(shadowData.getInt("stats/2"));
                shadow.setStats3(shadowData.getInt("stats/3"));
                shadow.setStats4(shadowData.getInt("stats/4"));
                shadow.setStats5(shadowData.getInt("stats/5"));
                shadow.setStats6(shadowData.getInt("stats/6"));
                users.add(shadow);
            }
        }catch (Exception ignore){
            ignore.printStackTrace();
        }

        return users;
    }
}