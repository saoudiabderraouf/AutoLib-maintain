package com.clovertech.autolib.Models

data class Auth_utilisateur (
    var idUtilisateur: Int,
    val type: String,
    val adresseEmail: String,
    val motDePasse: String
)