package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TacheModel
import com.clovertech.autolib.model.TaskModelToken
import com.clovertech.autolib.repository.NotificationRepo
import com.clovertech.autolib.repository.TacheRepo
import com.clovertech.autolib.ui.Notif
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

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