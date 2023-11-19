package com.example.p3re.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.p3re.apis.ShadowsAPI
import com.example.p3re.database.ShadowDAO
import com.example.p3re.data.Shadow
import com.example.p3re.database.AppDatabase
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ShadowsdbViewModel(application: Application) : AndroidViewModel(application) {

    val executor = Executors.newSingleThreadExecutor()

    var shadowsList by mutableStateOf<ArrayList<Shadow>>(ArrayList())

    private val shadowDAO: ShadowDAO

    /*private val db: ShadowDatabase = Room.databaseBuilder(
        application.applicationContext,
        ShadowDatabase::class.java, "shadow-database"
    ).build()*/

    val database: AppDatabase = AppDatabase.DatabaseBuilder.getDatabase(application.applicationContext)

    init {
        shadowDAO = database.getShadowDAO()

        viewModelScope.launch{
                executor.execute {
                    val api = ShadowsAPI()
                    val result = api.getShadows()

                    if (result != null) {
                        shadowDAO.insertAllShadows(result)
                    }
                    shadowsList = shadowDAO.getAllShadows() as ArrayList<Shadow>
                    /*handler.post {

                        result?.let { shadows ->
                            shadowsList = shadows
                        }
                    }*/
                }
            }
        }
    }


    /*init {
        shadowDAO = db.getShadowDAO()

        viewModelScope.launch {
            executor.execute {
                val api = ShadowsAPI()
                val result = api.getShadows()

                result?.let { shadows ->

                    shadowsList = shadows
                }
            }
        }
    }*/
