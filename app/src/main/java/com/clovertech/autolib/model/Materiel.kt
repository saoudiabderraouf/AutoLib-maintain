package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "usedEquipment")
data class Materiel(

    @Expose var description: String,
    @Expose var quantity: String

    ) {
    @PrimaryKey (autoGenerate = true) var idEquipment: Int = 0
}