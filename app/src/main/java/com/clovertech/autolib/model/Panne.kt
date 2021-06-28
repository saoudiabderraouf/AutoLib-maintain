package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.*

@Entity(tableName = "pannes")
data class Panne(
    @Expose @PrimaryKey val idPanne: Int,
    @Expose val dateNotification: Date,
    @Expose val dateReparationPanne: Date,
    @Expose val etat: String,
    @Expose val Description: String,
    @Expose val numChassis: Int,
    @Expose val idAgentEnvoyeNotif: Int,
    @Expose val idAgentTraitePanne: Int,
    @Expose val niveauSecurity: String
) {

}