package com.clovertech.autolib.Services

import com.clovertech.autolib.Models.Auth_utilisateur

import retrofit2.Response
import retrofit2.http.*


interface AuthApiService {
    @GET("user/{email}")
    fun getUserByEmail(@Path("email") email : String): Response<Auth_utilisateur>
}