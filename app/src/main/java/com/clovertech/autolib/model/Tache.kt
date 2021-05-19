package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*



@Entity(tableName = "taches")
data class Tache(
    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var idEtat: Int,
    var idMateriel: Int,

    ) {
    @PrimaryKey(autoGenerate = true)
    var idTache: Int? = null
}

