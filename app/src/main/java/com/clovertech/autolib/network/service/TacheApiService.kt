package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Tache
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TacheApiService {
    @GET("task/agent/{id}")
    suspend fun getTasksById(@Path("id") id: Int): Response<List<Tache>>
}