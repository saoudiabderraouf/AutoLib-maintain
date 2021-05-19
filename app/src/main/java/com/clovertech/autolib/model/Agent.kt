package com.clovertech.autolib.model

data class Agent(
    val idUtilisateur: Int = 0,
    val refPermis: Int = 0,
    val nom: String = "",
    val prenom: String = "",
    val adresse : String = "",
    val photoPersonnelle: String = ""
)