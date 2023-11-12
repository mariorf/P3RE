package com.example.p3re.utils

import android.net.Uri
import android.util.Log
import com.example.p3re.data.SHHADOW
import org.json.JSONArray
import java.io.IOException


class ShadowAPI {
    private val BASE_URL = "https://kcpdykkspiuyjmwvyxqa.supabase.co"
    private val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g"

    fun getShadows(): ArrayList<SHHADOW>? {
        val builtUri = Uri.parse(BASE_URL)
            .buildUpon()
            //nombre tabla
            .appendPath("p3_shadows")
            .appendQueryParameter("apikey", API_KEY)
            .build()

        val url = builtUri.toString()
        Log.d("DEBUG", url)

        return doCall(url)
    }

    private fun doCall(url: String): ArrayList<SHHADOW>? {
        try {
            var jsonResponse: String? = HttpUtils.get(url)

            return jsonResponse?.let { processJson(it) }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun processJson(jsonResponse: String): ArrayList<SHHADOW> {
        val shadowsList: ArrayList<SHHADOW> = ArrayList<SHHADOW>()
        Log.d("tuViejita", "Inicio processJson")
        try {
            val jsonUsers = JSONArray(jsonResponse)
            Log.d("ANGALEMEN", "JSON CREADO")

            for (i in 0 until jsonUsers.length()) {
                val shadowData = jsonUsers.getJSONObject(i)

                var shadow: SHHADOW? = null

                if (shadow != null) {
                    shadow.name=shadowData.getString("name")
                }

                if (shadow != null) {
                    shadow.name=shadowData.getString("area")
                }

                if (shadow != null) {
                    shadow.name=shadowData.getString("gem")
                }

                if (shadow != null) {
                    shadow.name=shadowData.getString("lvl")
                }
                if (shadow != null) {
                    shadow.name=shadowData.getString("race")
                }
                if (shadow != null) {
                    shadow.name=shadowData.getString("name")
                }


                if (shadow != null) {
                    shadowsList.add(shadow)
                }

            }
        } catch (ignore: Exception) {
            ignore.printStackTrace()
        }
        return shadowsList
    }
}