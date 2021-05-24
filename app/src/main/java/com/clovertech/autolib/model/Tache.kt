package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*



@Entity(tableName = "taches")
data class Tache(
    @PrimaryKey(autoGenerate = true) var idTask: Int,
    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var idTaskState: Int,
    var idEquipment: Int,

    ) {

}

