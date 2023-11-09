package com.example.p3re.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//En el dao se programa tot lo que te que vore en accedir y cambiar dades de la ddbb, en el meu cas soles necessite llegir
@Dao
interface ShadowDAO {

    @Insert
    suspend fun insertAll(shadows: List<Shadow>)

    @Query("SELECT * FROM shadow")
    fun getShadows(): Shadow
}