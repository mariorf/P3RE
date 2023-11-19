package com.example.p3re.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.p3re.data.Answers

@Dao
interface AnswersDAO {

    @Query("SELECT * FROM AnswersEntity")
    fun getAllAnswers(): List<Answers>

    @Insert(onConflict = REPLACE)
    fun insertAllAnswers(answersList: ArrayList<Answers>)

    @Delete
    fun deleteAllAnswers(shadowList: ArrayList<Answers>)

}