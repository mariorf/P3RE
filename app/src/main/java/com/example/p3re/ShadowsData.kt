package com.example.p3re

data class ShadowsData(
    val name: String,
    val area: String,
    val gem: String,
    val level: Int,
    val race: String,
    val resists: String,
    val skills: List<String>,
    val stats: List<Int>
)
