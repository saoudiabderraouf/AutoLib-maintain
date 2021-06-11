package com.clovertech.autolib.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Tasks")
data class Tache(

    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var taskTitle: String,
    var idTaskState: Int,
    var assignmentDate: Date,
    var endDate: Date?,
    var taskModel: TaskModelToken,
    var usedEquipements: List<Materiel>?,
    @PrimaryKey var uuid: String,
    var isSyns: Int = 0

) {

}

