package com.example.p3re.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ShadowAPIClient {

    val retrofit = Retrofit.Builder().baseUrl("https://kcpdykkspiuyjmwvyxqa.supabase.co").addConverterFactory(GsonConverterFactory.create()).build()

    val service = retrofit.create(ShadowsApiService::class.java)

}