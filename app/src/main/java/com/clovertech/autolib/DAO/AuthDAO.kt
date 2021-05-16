package com.clovertech.autolib.DAO

import androidx.room.Dao
import androidx.room.Query
import com.clovertech.autolib.Models.Auth_utilisateur

@Dao
interface AuthDAO {
    @Query("SELECT * FROM Auth_utilisateur")
    fun loadAll(): List<Auth_utilisateur>
}