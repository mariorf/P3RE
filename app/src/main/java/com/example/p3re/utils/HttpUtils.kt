package com.example.p3re.utils

import android.net.Uri
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
object HttpUtils {
    @Throws(IOException::class)
    operator fun get(dataUrl: String?): String? {
        val url = URL(dataUrl)
        var response: String?
        val urlConnection = url.openConnection() as HttpURLConnection
        response = try {
            val `in`: InputStream = BufferedInputStream(urlConnection.inputStream)
            readStream(`in`)
        } finally {
            urlConnection.disconnect()
        }
        return response
    }

    @Throws(IOException::class)
    private fun readStream(`in`: InputStream): String {
        val `is` = InputStreamReader(`in`)
        val rd = BufferedReader(`is`)
        var line: String?
        val response = StringBuilder()
        while (rd.readLine().also { line = it } != null) {
            response.append(line)
            response.append('\r')
        }
        rd.close()
        return response.toString()
    }
}