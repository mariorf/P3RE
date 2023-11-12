package com.example.p3re.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShadowsApiService {

    @GET("p3_shadows")
    fun listSahdows(@Query("api_key")apiKey: String): Call<SHHADOW>
}