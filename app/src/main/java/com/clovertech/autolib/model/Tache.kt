package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "taches")
data class Tache(
    @PrimaryKey(autoGenerate = true) var idTask: Int,
    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var taskTitle: String,
    var idTaskState: Int,
    var assignmentDate: String,
    var endDate: String,
    var steps: List<Step>,
    var usedEquipements: List<Materiel>,
    var uuid: Int

) {

}

