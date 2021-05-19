package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.clovertech.autolib.model.Tache
import retrofit2.http.DELETE

@Dao
interface TacheDAO {
    @Query("SELECT * from taches")
    fun getAllTaches(): LiveData<List<Tache>>

    @Insert
    fun insertAllTaches(tacheList: List<Tache>)

    @Insert
    suspend fun insertTache(tache: Tache)

    @Query("SELECT * FROM taches WHERE idTache LIKE :idT")
    fun getTacheById(idT: Int): Tache

    @DELETE
    fun deleteTcahe(tache: Tache)

    @Update
    fun updateTache(tache: Tache)


}