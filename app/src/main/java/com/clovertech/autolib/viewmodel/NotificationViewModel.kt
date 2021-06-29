package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.repository.NotificationRepo
import com.clovertech.autolib.views.ui.notifications.Notif

class NotificationViewModel : ViewModel() {


    fun postFCMToken (context: Context, agentToken: AgentToken) {
        NotificationRepo.postFCMToken(context, agentToken)
    }

    fun getAllNotifications(context: Context): LiveData<List<Notif>>{
        return NotificationRepo.getAllNotifications(context)
    }

    fun fetchAllNotifications(context: Context){
        NotificationRepo.fetchAllNotifications(context)
    }

}