package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.*

@Entity(tableName = "pannes")
data class Panne(

    @Expose var dateNotifPanne: String,
    @Expose var idAgentSentNotif: Int,
    @Expose var state: String,
    @Expose var idVehicle: String,
    @Expose var Description: String,
    @Expose var severityLevel: Int

) {
@PrimaryKey var id:Int = 0
}