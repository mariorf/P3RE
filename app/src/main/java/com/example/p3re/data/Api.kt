package com.example.p3re.data

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("https://kcpdykkspiuyjmwvyxqa.supabase.co/rest/v1/p3_shadows")
    fun getShadows(apiKey: String): Call<List<SHHADOW>>
}