package com.example.agent_app.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolib.AgentRepository
import com.clovertech.autolib.Models.Auth_utilisateur


class AgentViewModel(private val agentRepository: AgentRepository) : ViewModel(){
    val thisAgent : MutableLiveData<Auth_utilisateur> = agentRepository.thisUser
    fun getAgent(email:String){
        agentRepository.getAgent(email)
    }


}