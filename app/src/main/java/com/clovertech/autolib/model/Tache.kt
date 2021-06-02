package com.clovertech.autolib.model

import java.util.*


data class Tache(

    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var taskTitle: String,
    var idTaskState: Int,
    var assignmentDate: Date,
    var endDate: Date?,
    var taskModel: TaskModelToken,
    var usedEquipements: List<Materiel>,
    var uuid: String

) {

}

