package com.example.p3re.data

import android.app.Application
import androidx.room.Room

class ShadowApp:Application() {

    val room = Room.databaseBuilder(this, ShadowDatabase::class.java, "Shadows").build()
}