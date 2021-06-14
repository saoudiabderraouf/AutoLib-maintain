package com.clovertech.autolib.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.network.client.NotificationsApiClient
import com.clovertech.autolib.network.client.TacheApiClient
import com.clovertech.autolib.ui.Notif
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationRepo {
    companion object {

        val TAG = "LOG TAG"

        var appDb: AutolibDatabase? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        fun postFCMToken(context: Context, agentToken: AgentToken) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val request = NotificationsApiClient.notificationService.postToken(agentToken)
                if (request.isSuccessful){
                    Log.d(TAG, "postFCMToken: successful")
                }
            }

        }

        fun getAllNotifications(context: Context): LiveData<List<Notif>> {
            appDb = initializeDB(context)
            //CoroutineScope(Dispatchers.IO).launch {
            //}
            return appDb!!.notificationDao().getAllNotifications()
        }

        fun fetchAllNotifications(context: Context){
            appDb = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val request = NotificationsApiClient.notificationService.getAllNotifications()
                if (request.isSuccessful){
                    Log.d(TAG, "fetchAllNotifications: success")
                    for (n in request.body()!!){
                        appDb!!.notificationDao().insertNotification(n)
                    }
                } else {
                    Log.d(TAG, "fetchAllNotifications: failed")
                }
            }
        }


    }
}