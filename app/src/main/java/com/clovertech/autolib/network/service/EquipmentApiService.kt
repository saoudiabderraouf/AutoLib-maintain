package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Equipement
import com.clovertech.autolib.model.EquipementToken
import com.clovertech.autolib.model.NewEquipement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EquipmentApiService {
    @GET("equipment")
    suspend fun getAllEquipment(): Response<List<Equipement>>

    @POST("usedEquipment")
    suspend fun addNewUsedEquipment(@Body newequipement: NewEquipement): Response<EquipementToken>
}