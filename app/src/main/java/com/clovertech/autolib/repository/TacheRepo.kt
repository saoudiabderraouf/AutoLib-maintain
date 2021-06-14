package com.clovertech.autolib.repository

import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import com.clovertech.autolib.R
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TaskState
import com.clovertech.autolib.network.client.TacheApiClient
import com.clovertech.autolib.utils.DialogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TacheRepo {
    companion object {

        var appDb: AutolibDatabase? = null

        var taches: LiveData<List<Tache>>? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        /**
         * Inserer une tache dans la cache
         * @param tache
         * @param context*/

        fun insertTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().addTask(tache)
            }

        }

        /**
         * Recuperer la liste des taches existantes dans la cache
         * @return list<Tache>
         *  */


        fun getAllTaches(context: Context): LiveData<List<Tache>>? {

            appDb = initializeDB(context)

            taches = appDb!!.tacheDao().getAllTasks()

            return taches
        }

        /**
         * Recuperer la liste des taches d'un agent par son id du service et les insere une par une dans la cache
         * @param idAgent
         * */


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
         * 2 si  tache en cours
         * 3 si terminée*/
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

            var listFiltered = tache.steps?.filter { it.completed }
            if (listFiltered?.size == tache.steps?.size) {
                DialogUtils.with(context)
                    .showDialog(
                        context.getString(R.string.closeDialogTitle),
                        context.getString(R.string.closeMessagePre) + tache.taskTitle,
                        { dialogInterface: DialogInterface, i: Int ->
                            CoroutineScope(Dispatchers.IO).launch {
                                var Response =
                                    TacheApiClient.tacheApiService.updateTaskState(
                                        tache.uuid,
                                        TaskState(3)
                                    )
                                if (Response.isSuccessful) {
                                    tache.idTaskState = 3
                                    tache.endDate = Calendar.getInstance().time
                                }
                                appDb!!.tacheDao().updateTask(tache)
                            }
                        }, null
                    )
            } else {
                if (listFiltered?.size == 1)
                    CoroutineScope(Dispatchers.IO).launch {
                        var Response =
                            TacheApiClient.tacheApiService.updateTaskState(
                                tache.uuid,
                                TaskState(2)
                            )
                        if (Response.isSuccessful) {
                            tache.idTaskState = 2
                        }
                        appDb!!.tacheDao().updateTask(tache)
                    }
                else {
                    CoroutineScope(Dispatchers.IO).launch {
                        appDb!!.tacheDao().updateTask(tache)
                    }
                }
            }
        }
    }
}
