package com.example.p3re.apis

import android.net.Uri
import com.example.p3re.data.Shadow
import com.example.p3re.utils.HttpUtils.get
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException




var BASE_URL = "https://kcpdykkspiuyjmwvyxqa.supabase.co/rest/v1/"

var API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g" // Codi Proporcionat per themoviedb

public class ShadowsAPI{

    fun getShadows(): ArrayList<Shadow>? {
        val builtUri = Uri.parse(BASE_URL)
            .buildUpon()
            .appendPath("p3_shadows")
            .appendQueryParameter("apikey", API_KEY)
            .build()
        val url = builtUri.toString()
        return doCall(url)
    }
    private fun doCall(url: String): ArrayList<Shadow>? {
        try {
            val jsonResponse: String? = get(url)
            return jsonResponse?.let { processJson(it) }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    private fun processJson(jsonResponse: String): ArrayList<Shadow> {
        val shadowList = ArrayList<Shadow>()
        try {
            val jsonShadows: JSONArray = JSONArray(jsonResponse)
            for (i in 0 until jsonShadows.length()) {
                val jsonShadow = jsonShadows.getJSONObject(i)
                val shadow = Shadow("","","","",0,"","",0,0,0,0,0,0,0)
                shadow._key = jsonShadow.getString("_key")
                shadow.name = jsonShadow.getString("name")
                shadow.area = jsonShadow.getString("area")
                shadow.gem = jsonShadow.getString("gem")
                shadow.lvl = jsonShadow.getInt("lvl")
                shadow.race = jsonShadow.getString("race")
                shadow.resists = jsonShadow.getString("resists")
                shadow.stats0 = jsonShadow.getInt("stats0")
                shadow.stats1 = jsonShadow.getInt("stats1")
                shadow.stats2 = jsonShadow.getInt("stats2")
                shadow.stats3 = jsonShadow.getInt("stats3")
                shadow.stats4 = jsonShadow.getInt("stats4")
                shadow.stats5 = jsonShadow.getInt("stats5")
                shadow.stats6 = jsonShadow.getInt("stats6")
                shadowList.add(shadow)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return shadowList
    }
}
