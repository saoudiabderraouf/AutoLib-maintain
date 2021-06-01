package com.clovertech.autolib.model

data class Equipement(
    var uuid: String,
    var createdAt: String,
    var updatedAt: String,
    var equipmentName: String,
    var unitPrice: String,
    var category: String,
    var usedEquipments: List<UsedEquipment>
) {
}