package com.example.p3re.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import com.example.p3re.data.SHHADOW
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.ktor.util.Identity.decode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import androidx.lifecycle.lifecycleScope
import com.example.p3re.data.ShadowApp
import kotlinx.coroutines.coroutineScope



object SupabaseUtils {


    //val app = applicationContext as ShadowApp


    var shadowListResponse: List<SHHADOW>? = null

    var shadowList: ArrayList<SHHADOW>? = null


    //https://www.youtube.com/watch?v=NWaIIRfVpuo
    fun getClient() : SupabaseClient{

        val client = createSupabaseClient(

            supabaseUrl = "https://kcpdykkspiuyjmwvyxqa.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g"
        ){
            install(Postgrest)
        }
        return client
    }
    //https://www.youtube.com/shorts/aQw_97TqTQU
    suspend fun getData(): List<SHHADOW>? {

                    val client = getClient()
                    val supabaseResponse = client.postgrest["p3_shadows"].select()
                    shadowListResponse = supabaseResponse.decodeList<SHHADOW>()
                    for (SHHADOW in shadowListResponse!!){
                        Log.d("ACÁ", SHHADOW.name)
                    }
        return shadowListResponse
    }
}

