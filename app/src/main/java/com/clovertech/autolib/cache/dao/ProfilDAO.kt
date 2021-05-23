package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.clovertech.autolib.model.Agent

@Dao
interface ProfilDAO {
    @Query("SELECT * FROM agent WHERE idUtilisateur LIKE :idAgent")
    fun getThisProfil(idAgent: Int): Agent
    @Update
    fun updateProfil(agent: Agent)
    @Delete
    fun deleteProfil(agent: Agent)
}