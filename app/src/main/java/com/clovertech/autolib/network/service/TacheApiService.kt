package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Tache
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TacheApiService {
    @GET("task/agent/{idAgent}")
    suspend fun getTasksById(@Path("idAgent") id: Int): Response<List<Tache>>

}