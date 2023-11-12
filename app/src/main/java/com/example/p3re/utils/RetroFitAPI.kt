package com.example.p3re.utils

import com.example.p3re.data.SHHADOW
import retrofit2.http.GET

interface RetroFitAPI {

    @GET("https://kcpdykkspiuyjmwvyxqa.supabase.co/rest/v1/p3_shadows?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g")
    suspend fun getShadows() : List<SHHADOW>

}


