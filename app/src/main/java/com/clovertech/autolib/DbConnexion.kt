package com.clovertech.autolib

import android.content.Context
import androidx.room.Room
import com.clovertech.autolib.cache.db.AutolibDatabase

object DbConnexion {
    fun initDb(context: Context): AutolibDatabase {
        return Room.databaseBuilder(
           context  ,AutolibDatabase::class.java, "user-db").build()
    }
}