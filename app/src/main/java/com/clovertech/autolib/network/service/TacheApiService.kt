package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Tache
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TacheApiService {
    @GET("task")
    suspend fun getTasksById(): Response<List<Tache>>
    //ajouter ID apr
}