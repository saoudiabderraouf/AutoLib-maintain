package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TaskState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TacheApiService {
    @GET("task/agent/{idAgent}")
    suspend fun getTasksById(@Path("idAgent") id: Int): Response<List<Tache>>

    @POST("taskState/{uuid}")
    suspend fun updateTaskState(
        @Path("uuid") uuid: String,
        @Body taskState: TaskState
    ): Response<String>

}