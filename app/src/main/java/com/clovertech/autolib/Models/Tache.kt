package com.clovertech.autolib.Models

data class Tache (
    val idTache: Int,
    val idAgent : Int,
    val idVehicule: Int,
    val description: String,
    val idEtat: Int,
    val idMateriel: Int
)