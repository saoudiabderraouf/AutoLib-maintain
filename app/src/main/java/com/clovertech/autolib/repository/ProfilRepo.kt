package com.clovertech.autolib.repository

import com.clovertech.autolib.model.User
import com.clovertech.autolib.network.client.UserApiClient
import retrofit2.Response

class ProfilRepo {
    companion object {


         suspend fun getProfil(id:Int): Response<User>{
             return UserApiClient.userApiService.getUserById(id)

         }


    }
}