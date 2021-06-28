package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Panne
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PannApiService {
    @GET("/{idAgent}")
    suspend fun getPanneByIdAgent(@Path("idAgent") id: Int): Response<List<Panne>>
    @POST
    suspend fun validerPanne(panne: Panne): Response<String>
    @POST
    suspend fun deletePanne(panne: Panne): Response<String>
    @POST
    suspend fun insertPanne(panne: Panne): Response<String>
}