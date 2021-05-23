package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "etatTache")
data class EtatTache (

    var description: String,
    var avancement: String
){
    @PrimaryKey(autoGenerate = true) var idEtat: Int = 0
}


