package com.clovertech.autolib.model

data class Utilisateur (
    var idUser: Int,
    var userName: String,
    var lastName: String,
    var userType: Int,
    var firstName: String,
    var address: String,
    var phoneNumber: Long
)
