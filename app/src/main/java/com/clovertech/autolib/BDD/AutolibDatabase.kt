package com.clovertech.autolib.BDD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clovertech.autolib.DAO.AuthDAO
import com.clovertech.autolib.Models.Auth_utilisateur

@Database (entities = arrayOf(Auth_utilisateur::class), version = 1)
abstract class AutolibDatabase : RoomDatabase(){
    abstract fun authDao(): AuthDAO
}