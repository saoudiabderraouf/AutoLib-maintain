package com.clovertech.autolib.ViewModelsFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.AgentRepository
import com.clovertech.autolib.ViewModels.AgentViewModel

@Suppress("UNCHECKED_CAST")
class AgentViewModelFactory(private val agentRepository: AgentRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>)
    : T = AgentViewModel(agentRepository) as T

}