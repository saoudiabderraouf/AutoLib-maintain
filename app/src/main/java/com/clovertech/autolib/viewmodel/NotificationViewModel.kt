package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.repository.NotificationRepo
import com.clovertech.autolib.ui.notifications.Notif
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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