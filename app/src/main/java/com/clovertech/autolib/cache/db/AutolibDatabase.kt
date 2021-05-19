package com.clovertech.autolib.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.clovertech.autolib.cache.converter.Converters
import com.clovertech.autolib.cache.dao.AuthDAO
import com.clovertech.autolib.cache.dao.EtatDAO
import com.clovertech.autolib.cache.dao.ProfilDAO
import com.clovertech.autolib.cache.dao.TacheDAO
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.model.Tache

@Database(entities = arrayOf(Auth_utilisateur::class, Tache::class, Agent::class), version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AutolibDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDAO
    abstract fun tacheDao(): TacheDAO
    abstract fun profilsDao(): ProfilDAO
    abstract fun etatDao(): EtatDAO

    companion object {

        @Volatile
        private var INSTANCE: AutolibDatabase? = null

        fun getDatabaseClient(context: Context): AutolibDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AutolibDatabase::class.java, "cache_db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}