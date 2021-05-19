package com.clovertech.autolib.repository

import androidx.lifecycle.MutableLiveData
import com.clovertech.autolib.model.Auth_utilisateur
import com.clovertech.autolib.network.service.AuthService

class AgentRepository (private val authService: AuthService){
    val thisUser = MutableLiveData<Auth_utilisateur>()
   fun getAgent(email:String){
       thisUser.value= authService.getUserByEmail(email)
   }

}