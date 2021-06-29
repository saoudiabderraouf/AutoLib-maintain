package com.clovertech.autolib.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.model.PanneResponse
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.network.client.PanneApiClient
import com.clovertech.autolib.network.client.TacheApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PanneRepo {
    companion object {

        var appDb: AutolibDatabase? = null

        var pannes: LiveData<List<Panne>>? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }



        fun getAllPannes(context: Context): LiveData<List<Panne>>? {

            PanneRepo.appDb = PanneRepo.initializeDB(context)

            PanneRepo.pannes = PanneRepo.appDb!!.panneDao().getAllPannes()

            return PanneRepo.pannes
        }


        suspend fun addPanne(panne:Panne): Response<PanneResponse> {
            return PanneApiClient.panneApiService.insertPanne(panne)
        }


    }
}