package com.clovertech.autolib.model

data class Vehicule (
    val idVehicule : Int ,
    val matricule: Int ,
    val categorieVehicule: String,
    val marqueVehicule : String ,
    val modeleVehicule: String ,
    val typeCarburant: String ,
    val prixUnitaireHeure: Float ,
    val prixUnitaireJour:   Float ,
    val idBorne: Int ,
    val couleurVehicule: Float,
    val longitude: Float,
    val lattitude: Float
)