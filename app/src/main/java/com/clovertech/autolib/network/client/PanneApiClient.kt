package com.clovertech.autolib.network.client

import com.clovertech.autolib.network.service.PannApiService
import com.clovertech.autolib.network.service.TacheApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PanneApiClient {
    private val gson : Gson by lazy {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://3353aa3edbce.ngrok.io/addPanne/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val panneApiService: PannApiService by lazy {
        retrofit.create(PannApiService::class.java)
    }
}