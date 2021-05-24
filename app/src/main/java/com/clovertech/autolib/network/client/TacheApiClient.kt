package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.AuthApiService
import com.clovertech.autolib.network.service.TacheApiService
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

        Retrofit.Builder()
            .baseUrl("http://0c5dcb43aba5.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val tacheApiService : TacheApiService by lazy {
        retrofit.create(TacheApiService::class.java)

    }

}