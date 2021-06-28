package com.clovertech.autolib.model

data class Utilisateur(
    var idUser: String,
    var userName: String,
    var lastName: String,
    var userType: String,
    var firstName: String,
    var address: String,
    var phoneNumber: String,
    var creationDate: String
)
