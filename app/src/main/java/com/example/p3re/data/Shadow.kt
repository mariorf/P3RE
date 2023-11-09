package com.example.p3re.data

import com.example.p3re.data.Converters
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

//Entity = tabla
@Entity()
@TypeConverters(Converters::class)
data class Shadow(

    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val area: String,
    val gem: String,
    val lvl: Int,
    val race: String,
    val resists: String,
    val skills: List<String>,
    val stats: List<Int>
)