package com.clovertech.autolib.repository

import androidx.lifecycle.MutableLiveData
import com.clovertech.autolib.model.Auth_utilisateur

class AgentRepository (){
    val thisUser = MutableLiveData<Auth_utilisateur>()
   fun getAgent(email:String){
       
       //thisUser.value= authService.getUserByEmail(email)
   }

}