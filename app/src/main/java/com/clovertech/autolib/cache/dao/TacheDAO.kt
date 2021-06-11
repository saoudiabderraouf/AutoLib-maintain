package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clovertech.autolib.model.Tache
import retrofit2.http.DELETE

@Dao
interface TacheDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(tache: Tache)

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): LiveData<List<Tache>>


}