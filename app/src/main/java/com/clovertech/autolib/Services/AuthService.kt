package com.clovertech.autolib.Services

import com.clovertech.autolib.Clients.AuthApiClient
import com.clovertech.autolib.Models.Auth_utilisateur

class AuthService {
    fun getUserByEmail(email:String): Auth_utilisateur? {
        var authUtilisateur:Auth_utilisateur= Auth_utilisateur(1,"agent", "agent@autolib.dz", "admin")

        //return AuthApiClient.authApiService.getUserByEmail(email).body()
        return authUtilisateur
    }
}