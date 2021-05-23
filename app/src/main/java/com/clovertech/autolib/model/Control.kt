package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "control")
data class Control(

    var date: Date,
    var idTache: Int
) {
    @PrimaryKey(autoGenerate = true)
    var idControl: Int = 0
}