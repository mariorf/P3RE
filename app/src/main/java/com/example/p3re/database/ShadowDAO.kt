package com.example.p3re.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.p3re.data.Shadow

//En el dao se programa tot lo que te que vore en accedir y cambiar dades de la ddbb, en el meu cas soles necessite llegir
@Dao
interface ShadowDAO {

    @Query("SELECT * FROM ShadowsEntity")
    fun getAllShadows(): List<Shadow>

    @Insert(onConflict = REPLACE)
    fun insertAllShadows(shadowList: ArrayList<Shadow>)

    @Delete
    fun deleteAllShadows(shadowList: ArrayList<Shadow>)

}