package com.clovertech.autolib.model


data class Tache(

    var idAgent: Int,
    var idVehicule: Int,
    var description: String,
    var taskTitle: String,
    var idTaskState: Int,
    var assignmentDate: String,
    var endDate: String?,
    var taskModel: TaskModelToken,
    var usedEquipements: List<Materiel>,
    var uuid: String

) {

}

