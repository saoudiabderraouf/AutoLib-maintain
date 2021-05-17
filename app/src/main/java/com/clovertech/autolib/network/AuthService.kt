package com.clovertech.autolib.network

import com.clovertech.autolib.model.Auth_utilisateur

class AuthService {
    fun getUserByEmail(email:String): Auth_utilisateur? {
        var authUtilisateur:Auth_utilisateur= Auth_utilisateur(1,"agent", "agent@autolib.dz", "admin")

        //return AuthApiClient.authApiService.getUserByEmail(email).body()
        return authUtilisateur
    }
}