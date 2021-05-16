package com.clovertech.autolib

import androidx.lifecycle.MutableLiveData
import com.clovertech.autolib.Clients.AuthApiClient
import com.clovertech.autolib.Models.Auth_utilisateur
import com.clovertech.autolib.Services.AuthApiService
import com.clovertech.autolib.Services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentRepository (private val authService: AuthService){
    val thisUser = MutableLiveData<Auth_utilisateur>()
   fun getAgent(email:String){
       thisUser.value= authService.getUserByEmail(email)
   }

}