package com.clovertech.autolib.Injections

import com.clovertech.autolib.AgentRepository
import com.clovertech.autolib.Services.AuthApiService
import com.clovertech.autolib.Services.AuthService
import com.clovertech.autolib.ViewModelsFactories.AgentViewModelFactory

object AuthInjection {
    val authService: AuthService by lazy { AuthService() }
    val agentRepository: AgentRepository by lazy { AgentRepository(authService) }
    val agentViewModelFactory : AgentViewModelFactory by lazy { AgentViewModelFactory(agentRepository) }
}