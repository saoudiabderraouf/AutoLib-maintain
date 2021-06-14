package com.clovertech.autolib.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TacheModel
import com.clovertech.autolib.model.TaskState
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

        /**
         * Inserer une tache dans la cache
         * @param tache*/

        fun insertTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().addTask(tache)
            }

        }

        /**
         * Recuperer la liste des taches existantes dans la cache
         * @return list<Tache>
         *     */


        fun getAllTaches(context: Context): LiveData<List<Tache>>? {

            appDb = initializeDB(context)

            taches = appDb!!.tacheDao().getAllTasks()

            return taches
        }

        /**
         * Recuperer la liste des taches d'un agent par son id du service et les insere une par une dans la cache
         * @param idAgent*/


        suspend fun getTacheIdAgent(context: Context, id: Int) {
            var Response = TacheApiClient.tacheApiService.getTasksById(id)

            if (Response.isSuccessful) {
                var lisTache = Response.body()!!
                for (tache in lisTache) {
                    insertTache(context, tache)
                }
            }
        }



        /**
         * Mettre a jour l'etat d'une tache dans le service
         * @param tache
         * si 2 en cours
         * si 3 terminée*/
        suspend fun updateStatetask(tache: Tache) {
            var listFiltered = tache.steps?.filter { it.completed == true }
            if (listFiltered?.size == 1) {
                var Response =
                    TacheApiClient.tacheApiService.updateTaskState(tache.uuid, TaskState(2))
                if (Response.isSuccessful) {
                    tache.idTaskState = 2
                }
            } else {
                if (listFiltered?.size == tache.steps?.size) {
                    var Response =
                        TacheApiClient.tacheApiService.updateTaskState(tache.uuid, TaskState(3))
                    if (Response.isSuccessful) {
                        tache.idTaskState = 3
                    }
                }

            }

        }

        /**
         * Mettre à jour l'etat des etapes d'une tache dans la cache
         * @param context
         * @param tache*/

        fun updateTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                var listFiltered = tache.steps?.filter { it.completed == true }
                if (listFiltered?.size == 1) {
                    var Response =
                        TacheApiClient.tacheApiService.updateTaskState(tache.uuid, TaskState(2))
                    if (Response.isSuccessful) {
                        tache.idTaskState = 2
                    }
                } else {
                    if (listFiltered?.size == tache.steps?.size) {
                        var Response =
                            TacheApiClient.tacheApiService.updateTaskState(tache.uuid, TaskState(3))
                        if (Response.isSuccessful) {
                            tache.idTaskState = 3
                        }
                    }

                }


                appDb!!.tacheDao().updateTask(tache)
            }

        }


    }
}