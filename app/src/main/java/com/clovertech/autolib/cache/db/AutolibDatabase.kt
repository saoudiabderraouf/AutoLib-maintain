package com.clovertech.autolib.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clovertech.autolib.cache.dao.AuthDAO
import com.clovertech.autolib.cache.dao.TacheDAO
import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.model.Tache

@Database (entities = arrayOf(Auth_utilisateur::class, Tache::class), version = 3)
abstract class AutolibDatabase : RoomDatabase(){
    abstract fun authDao(): AuthDAO
    abstract fun tacheDao(): TacheDAO
}