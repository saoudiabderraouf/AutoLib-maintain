package com.clovertech.autolib.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Tache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TacheRepo {
    companion object {

        var appDb: AutolibDatabase? = null

        var taches: LiveData<List<Tache>>? = null

        fun initializeDB(context: Context) : AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }

        fun insertAllTaches(context: Context, taches: List<Tache>) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().insertAllTaches(taches)
            }

        }
        fun insertTache(context: Context, tache: Tache) {

            appDb = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                appDb!!.tacheDao().insertTache(tache)
            }

        }

        fun getAllTaches(context: Context) : LiveData<List<Tache>>? {

            appDb = initializeDB(context)

            taches = appDb!!.tacheDao().getAllTaches()

            return taches
        }

    }
}