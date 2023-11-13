package com.example.p3re.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun getShadowsFromURL(){


    CoroutineScope(Dispatchers.Main).launch {

        val url = URL("https://kcpdykkspiuyjmwvyxqa.supabase.co/rest/v1/p3_shadows?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            val responseText = inputStream.bufferedReader().use {
                it.readText()
            }

            val json = Json { ignoreUnknownKeys = true }
            val shadowList = json.decodeFromString<List<SHHADOW>>(responseText)

        }
    }
}