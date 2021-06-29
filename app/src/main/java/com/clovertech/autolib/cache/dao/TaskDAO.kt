package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clovertech.autolib.model.Tache

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Tache)

    @Update
    suspend fun updateTask(task: Tache)

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): LiveData<List<Tache>>


}