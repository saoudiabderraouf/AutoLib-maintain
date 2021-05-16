package com.clovertech.autolib

import androidx.lifecycle.MutableLiveData
import com.clovertech.autolib.Clients.AuthApiClient
import com.clovertech.autolib.Models.Auth_utilisateur
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentRepository {
    val thisUser = MutableLiveData<Auth_utilisateur>()
   fun getAgent(email:String){
          try {
                val response = AuthApiClient.authApiService.getUserByEmail(email)

                if (response.isSuccessful && response.body() != null) {
                    thisUser.value = response.body()

                }

          } catch (e: Exception) {

          }

    }
}