package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.AuthApiService
import com.clovertech.autolib.network.service.TacheApiService
import com.clovertech.autolib.network.service.TacheModelApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TacheApiClient {
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        Retrofit.Builder()
            .baseUrl("https://volet-maintenance.herokuapp.com/service-task/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }


    val tacheApiService : TacheApiService by lazy {
        retrofit.create(TacheApiService::class.java)

    }
    val tacheModelApiService : TacheModelApiService by lazy {
        retrofit.create(TacheModelApiService::class.java)

    }

}