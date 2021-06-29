package com.clovertech.autolib.repository

import android.content.Context
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.model.Utilisateur
import com.clovertech.autolib.network.client.UserApiClient
import retrofit2.Response

class ProfilRepo {
    companion object {


         suspend fun getProfil(id:Int): Response<Utilisateur>{
             return UserApiClient.utilisateurApiService.getUserById(id)

         }


    }
}