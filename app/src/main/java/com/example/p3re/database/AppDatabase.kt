package com.example.p3re.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.p3re.data.Shadow
import androidx.room.Room
import com.example.p3re.data.Answers

@Database(entities = [Shadow::class, Answers::class], version = 1)
abstract class AppDatabase:RoomDatabase(){

    object DatabaseBuilder {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "app-database"
                ).build()
            }
            return INSTANCE!!
        }
    }

    abstract fun getShadowDAO(): ShadowDAO

    abstract fun getAnswersDAO(): AnswersDAO
}