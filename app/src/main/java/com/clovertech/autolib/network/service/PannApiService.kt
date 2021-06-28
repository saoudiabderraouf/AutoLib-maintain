package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Panne
import retrofit2.Response
import retrofit2.http.*

interface PannApiService {

    @POST
    suspend fun insertPanne(panne: Panne): Response<Panne>
}