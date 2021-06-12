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

class TacheRepo {
    companion object {

        var appDb: AutolibDatabase? = null

        var taches: LiveData<List<Tache>>? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        fun insertTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().addTask(tache)
            }

        }


        fun getAllTaches(context: Context): LiveData<List<Tache>>? {

            appDb = initializeDB(context)

            taches = appDb!!.tacheDao().getAllTasks()

            return taches
        }

        fun getAllModelTaches(context: Context): LiveData<List<TacheModel>>? {

            appDb = initializeDB(context)

            val taches = appDb!!.taskModelDao().getAllTasks()

            return taches
        }

        suspend fun getTacheIdAgent(context: Context, id: Int) {
            var Response = TacheApiClient.tacheApiService.getTasksById(id)

            if (Response.isSuccessful) {
                var lisTache = Response.body()!!
                for (tache in lisTache) {
                    insertTache(context, tache)
                }
            }
        }

        fun insertTacheModel(context: Context, tacheModel: TacheModel) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.taskModelDao().addTaskModel(tacheModel)
            }

        }

        suspend fun getAllTacheModel(context: Context) {
            var Response = TacheApiClient.tacheModelApiService.getAllTacheModel()
            if (Response.isSuccessful) {
                var listSteps = Response.body()!!
                for (tacheModel in listSteps) {
                    insertTacheModel(context, tacheModel)
                }

            }
        }

         fun updateTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().updateTask(tache)
            }

        }


    }
}