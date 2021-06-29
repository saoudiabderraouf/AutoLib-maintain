package com.clovertech.autolib.repository

import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import com.clovertech.autolib.R
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Task
import com.clovertech.autolib.model.TaskState
import com.clovertech.autolib.network.client.TaskApiClient
import com.clovertech.autolib.utils.DialogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TacheRepo {

    companion object {

        var appDb: AutolibDatabase? = null

        var taches: LiveData<List<Task>>? = null

        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        /**
         * Inserer une tache dans la cache
         * @param task
         * @param context*/

        fun insertTache(context: Context, task: Task) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.taskDao().addTask(task)
            }

        }

        /**
         * Recuperer la liste des taches existantes dans la cache
         * @return list<Tache>
         *  */


        fun getAllTaches(context: Context): LiveData<List<Task>>? {

            appDb = initializeDB(context)

            taches = appDb!!.taskDao().getAllTasks()

            return taches
        }

        /**
         * Recuperer la liste des taches d'un agent par son id du service et les insere une par une dans la cache
         * @param idAgent
         * */


        suspend fun getTacheIdAgent(context: Context, id: Int) {
            val Response = TaskApiClient.TASK_API_SERVICE.getTasksById(id)

            if (Response.isSuccessful) {
                var lisTache = Response.body()!!
                for (tache in lisTache) {
                    insertTache(context, tache)
                }
            }
        }


        /**
         * Mettre a jour l'etat d'une tache dans le service
         * @param task
         * 2 si  tache en cours
         * 3 si terminée*/
        suspend fun updateStatetask(task: Task) {
            var listFiltered = task.steps?.filter { it.completed == true }
            if (listFiltered?.size == 1) {
                var Response =
                    TaskApiClient.TASK_API_SERVICE.updateTaskState(task.uuid, TaskState(2))
                if (Response.isSuccessful) {
                    task.idTaskState = 2
                }
            } else {
                if (listFiltered?.size == task.steps?.size) {
                    var Response =
                        TaskApiClient.TASK_API_SERVICE.updateTaskState(task.uuid, TaskState(3))
                    if (Response.isSuccessful) {
                        task.idTaskState = 3
                    }
                }

            }

        }

        /**
         * Mettre à jour l'etat des etapes d'une tache dans la cache
         * @param context
         * @param task*/

        fun updateTache(context: Context, task: Task) {

            appDb = initializeDB(context)

            var listFiltered = task.steps?.filter { it.completed }
            if (listFiltered?.size == task.steps?.size) {
                DialogUtils.with(context)
                    .showDialog(
                        context.getString(R.string.closeDialogTitle),
                        context.getString(R.string.closeMessagePre) + task.taskTitle,
                        { dialogInterface: DialogInterface, i: Int ->
                            CoroutineScope(Dispatchers.IO).launch {
                                var Response =
                                    TaskApiClient.TASK_API_SERVICE.updateTaskState(
                                        task.uuid,
                                        TaskState(3)
                                    )
                                if (Response.isSuccessful) {
                                    task.idTaskState = 3
                                    task.endDate = Calendar.getInstance().time
                                }
                                appDb!!.taskDao().updateTask(task)
                            }
                        }, null
                    )
            } else {
                if (listFiltered?.size == 1)
                    CoroutineScope(Dispatchers.IO).launch {
                        var Response =
                            TaskApiClient.TASK_API_SERVICE.updateTaskState(
                                task.uuid,
                                TaskState(2)
                            )
                        if (Response.isSuccessful) {
                            task.idTaskState = 2
                        }
                        appDb!!.taskDao().updateTask(task)
                    }
                else {
                    CoroutineScope(Dispatchers.IO).launch {
                        appDb!!.taskDao().updateTask(task)
                    }
                }
            }
        }
    }
}

