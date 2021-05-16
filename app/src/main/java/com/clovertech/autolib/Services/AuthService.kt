package com.clovertech.autolib.Services

import com.clovertech.autolib.Clients.AuthApiClient
import com.clovertech.autolib.Models.Auth_utilisateur

class AuthService {
    fun getUserByEmail(email:String): Auth_utilisateur? {
        return AuthApiClient.authApiService.getUserByEmail(email).body()
    }
}