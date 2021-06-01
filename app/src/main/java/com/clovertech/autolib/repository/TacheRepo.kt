package com.clovertech.autolib.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TacheModel
import com.clovertech.autolib.network.client.TacheApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class TacheRepo {
    companion object {

        var appDb: AutolibDatabase? = null

        var taches: LiveData<List<Tache>>? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        fun insertAllTaches(context: Context, taches: List<Tache>) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                // appDb!!.tacheDao().insertAllTaches(taches)
            }

        }

        fun insertTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                // appDb!!.tacheDao().insertTache(tache)
            }

        }

        /*fun getTacheById(context: Context, id: Int): Tache {
            appDb = initializeDB(context)
            return appDb!!.tacheDao().getTacheById(id)
        }*/

        fun updateTache(context: Context, tache: Tache) {
            appDb = initializeDB(context)
            //return appDb!!.tacheDao().updateTache(tache)

        }

        fun getAllTaches(context: Context): LiveData<List<Tache>>? {

            appDb = initializeDB(context)

            //taches = appDb!!.tacheDao().getAllTaches()

            return null
        }

        suspend fun getTacheIdAgent(id: Int): Response<List<Tache>> {
            return TacheApiClient.tacheApiService.getTasksById(id)
        }

        suspend fun getTacheModelById(id: Int): Response<TacheModel> {
            return TacheApiClient.tacheModelApiService.getTacheModelById(id)
        }

    }
}