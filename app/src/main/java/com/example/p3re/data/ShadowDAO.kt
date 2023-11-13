package com.example.p3re.data

import androidx.compose.ui.graphics.Shadow
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//En el dao se programa tot lo que te que vore en accedir y cambiar dades de la ddbb, en el meu cas soles necessite llegir
@Dao
interface ShadowDAO {

    @Query("SELECT * FROM SHHADOW")
    fun getAll(): List<SHHADOW>

    @Query("SELECT * FROM SHHADOW WHERE name = :name")
    fun getByName(name: String): SHHADOW

    @Insert
    fun insertShadow(shadow:SHHADOW)

}