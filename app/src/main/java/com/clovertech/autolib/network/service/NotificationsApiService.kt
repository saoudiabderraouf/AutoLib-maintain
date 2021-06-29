package com.clovertech.autolib.network.service

import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.views.ui.notifications.Notif
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationsApiService {

    @POST("ATNotif")
    suspend fun postToken(@Body agentToken: AgentToken): Response<Object>


    @GET("taskNotif")
    suspend fun getAllNotifications(): Response<List<Notif>>
}