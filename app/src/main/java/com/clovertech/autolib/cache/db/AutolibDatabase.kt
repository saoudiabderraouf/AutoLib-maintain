package com.clovertech.autolib.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.clovertech.autolib.cache.converter.Converters
import com.clovertech.autolib.cache.dao.*
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.model.Step

@Database(
    entities = arrayOf(Auth_utilisateur::class, Agent::class, Step::class),
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AutolibDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDAO
    abstract fun tacheDao(): TacheDAO
    abstract fun profilsDao(): ProfilDAO
    abstract fun etatDao(): EtatDAO
    abstract fun stepDao(): StepDAO


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