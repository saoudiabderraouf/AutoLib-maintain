package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clovertech.autolib.model.Tache

@Dao
interface TacheDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(tache: Tache)

    @Update
    suspend fun updateTask(tache: Tache)

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): LiveData<List<Tache>>


}