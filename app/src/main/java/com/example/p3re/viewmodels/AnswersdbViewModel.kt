package com.example.p3re.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.p3re.apis.AnswersAPI
import com.example.p3re.data.Answers
import com.example.p3re.database.AnswersDAO
import com.example.p3re.database.AppDatabase
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class AnswersdbViewModel(application: Application) : AndroidViewModel(application) {

    val executor = Executors.newSingleThreadExecutor()

    var answersList by mutableStateOf<ArrayList<Answers>>(ArrayList())

    //private val answersDAO: AnswersDAO()

    val database: AppDatabase = AppDatabase.DatabaseBuilder.getDatabase(application.applicationContext)

    init {
        val answersDAO = database.getAnswersDAO()

        viewModelScope.launch{
            executor.execute {
                val api = AnswersAPI()
                val result = api.getAnswers()
                if (result != null) {
                    answersDAO.insertAllAnswers(result)
                }
                answersList = answersDAO.getAllAnswers() as ArrayList<Answers>
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
