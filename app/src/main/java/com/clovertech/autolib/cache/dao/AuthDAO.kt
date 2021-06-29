package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.clovertech.autolib.model.Auth_utilisateur

@Dao
interface AuthDAO {

    @Query("SELECT * FROM auth_users")
    fun loadAllAuthUsers(): List<Auth_utilisateur>
}