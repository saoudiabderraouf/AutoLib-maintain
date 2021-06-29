package com.clovertech.autolib.repository

import com.clovertech.autolib.model.User
import com.clovertech.autolib.network.client.UserApiClient
import retrofit2.Response

object ProfileRepo {
     suspend fun getProfile(id:Int): Response<User>{
         return UserApiClient.userApiService.getUserById(id)
     }
}