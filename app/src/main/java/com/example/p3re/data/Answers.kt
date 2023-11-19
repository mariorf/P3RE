package com.example.p3re.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "AnswersEntity")
@Serializable
data class Answers(
    @PrimaryKey
    var _key :String,
    var date: String,
    var Question: String,
    var Answer: String
)
