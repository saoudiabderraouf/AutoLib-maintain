package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "taches")
data class Tache (
    @PrimaryKey (autoGenerate = true) val idTache: Int,
    @ColumnInfo (name = "idAgent") val idAgent : Int,
    @ColumnInfo val idVehicule: Int,
    @ColumnInfo val description: String,
    @ColumnInfo val idEtat: Int,
    @ColumnInfo val idMateriel: Int
)