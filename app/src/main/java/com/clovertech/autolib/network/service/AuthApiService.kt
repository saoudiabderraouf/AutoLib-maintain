package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.Token
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface AuthApiService {
    @POST("signin")
    suspend fun getUserByInfo(@Body login: Login): Response<Token>

}