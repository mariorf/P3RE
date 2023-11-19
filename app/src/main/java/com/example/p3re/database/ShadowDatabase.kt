package com.example.p3re.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.p3re.data.Shadow
import androidx.room.Room

@Database(entities = [Shadow::class], version = 1)
abstract class ShadowDatabase:RoomDatabase(){
    object DatabaseBuilder {
        private var INSTANCE: ShadowDatabase? = null

        fun getDatabase(context: Context): ShadowDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ShadowDatabase::class.java, "db"
                ).build()
            }
            return INSTANCE!!
        }
    }
    abstract fun getShadowDAO(): ShadowDAO
}