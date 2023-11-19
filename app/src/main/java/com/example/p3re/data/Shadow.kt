package com.example.p3re.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "ShadowsEntity")
@Serializable
data class Shadow(

    @PrimaryKey
    var _key: String,
    var name: String,
    var area: String,
    var gem: String,
    var lvl: Int,
    var race: String,
    var resists: String,

    var stats0: Int,
    var stats1: Int,
    var stats2: Int,
    var stats3: Int,
    var stats4: Int,
    var stats5: Int,
    var stats6: Int

){
}





