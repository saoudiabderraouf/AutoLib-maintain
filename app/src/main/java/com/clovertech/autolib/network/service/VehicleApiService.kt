package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("/vehicle/{id}")
    fun getVehicles(@Path("id")id: Int): Response<Vehicle>

}