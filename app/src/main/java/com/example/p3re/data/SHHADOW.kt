package com.example.p3re.data

import kotlinx.serialization.Serializable

@Serializable
data class SHHADOW(

    var _key: String,
    var name: String,
    val area: String,
    val gem: String,
    val lvl: Int,
    val race: String,
    val resists: String,

    val stats0: Int,
    val stats1: Int,
    val stats2: Int,
    val stats3: Int,
    val stats4: Int,
    val stats5: Int,
    val stats6: Int

){
}





