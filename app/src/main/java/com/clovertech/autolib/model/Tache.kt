package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "taches")
data class Tache(
    var idAgent: Int,
    var idVehicule: Int,
    var taskTitle: String,
    var description: String,
    var idTaskState: Int,
    var assignmentDate: String,
    var endDate: String,
    var uuid: String

) {
    @PrimaryKey(autoGenerate = true) var idTask: Int = 0
}

