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
import kotlinx.coroutines.coroutineScope


class SupabaseUtils() {



    //https://www.youtube.com/watch?v=NWaIIRfVpuo
    fun getClient() : SupabaseClient{

        val client = createSupabaseClient(

            supabaseUrl = "https://kcpdykkspiuyjmwvyxqa.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtjcGR5a2tzcGl1eWptd3Z5eHFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTk3MTMxMTMsImV4cCI6MjAxNTI4OTExM30.phkffCnV3l_i3KtM4VRjfeTWHz_d50JnEj1v0oCJh-g"
        ){
            Log.d("DEBUG", "HOLA1")
            install(Postgrest)
            Log.d("DEBUG", "HOLA2")
        }
        return client
    }
    //https://www.youtube.com/shorts/aQw_97TqTQU
    fun getData(){


                GlobalScope.launch {
                    Log.d("DEBUG", "HOLA1")
                    val client = getClient()
                    val supabaseResponse = client.postgrest["p3_shadows"].select()
                    val data = supabaseResponse.decodeList<SHHADOW>()
                    Log.d("DEBUG", "HOLA2")
                }
    }
}

