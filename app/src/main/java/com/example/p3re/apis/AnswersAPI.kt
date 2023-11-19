package com.example.p3re.apis

import android.net.Uri
import android.util.Log
import com.example.p3re.data.Answers
import com.example.p3re.data.Shadow
import com.example.p3re.utils.HttpUtils.get
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException


//La base URL y API_KEY la coge directamente de ShadowsAPI
class AnswersAPI{

    fun getAnswers(): ArrayList<Answers>? {
        val builtUri = Uri.parse(BASE_URL)
            .buildUpon()
            .appendPath("p3_answers")
            .appendQueryParameter("apikey", API_KEY)
            .build()
        val url = builtUri.toString()
        Log.d("KYS", url)
        return doCall(url)
    }
    private fun doCall(url: String): ArrayList<Answers>? {
        try {
            val jsonResponse: String? = get(url)
            return jsonResponse?.let { processJson(it) }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    private fun processJson(jsonResponse: String): ArrayList<Answers> {
        val answersList = ArrayList<Answers>()
        try {
            val jsonAnswers: JSONArray = JSONArray(jsonResponse)
            for (i in 0 until jsonAnswers.length()) {
                val jsonAnswers = jsonAnswers.getJSONObject(i)
                val answers = Answers("", "", "", "")
                answers._key = jsonAnswers.getString("_key")
                answers.date = jsonAnswers.getString("date")
                answers.Answer = jsonAnswers.getString("Answer")
                answers.Question = jsonAnswers.getString("Question")
                answersList.add(answers)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return answersList
    }
}
