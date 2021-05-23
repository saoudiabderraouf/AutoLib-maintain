package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.clovertech.autolib.model.Control

@Dao
interface ControlDAO {

    @Query("SELECT * FROM control")
    fun getAllControl(): List<Control>
    @Query("SELECT * FROM control WHERE idTache=:id")
    fun getControlById(id : Int): Control


}