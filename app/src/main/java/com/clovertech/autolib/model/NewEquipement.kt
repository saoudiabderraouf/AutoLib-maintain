package com.clovertech.autolib.model

data class NewEquipement(
    var description: String,
    var quantity: Int,
    var equipment: String,
    var taskUUID: String

) {}