package com.clovertech.autolib.Models

data class Materiel(
    val idMateriel: Int,
    val nomMateriel: String,
    val quantite: Int,
    val prixUnitaire: Float,
    val description: String,
    val idTache: Int,
    val parentIdMateriel: Int
)