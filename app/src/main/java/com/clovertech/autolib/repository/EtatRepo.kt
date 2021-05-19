package com.clovertech.autolib.repository

import android.content.Context
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.EtatTache

class EtatRepo {
    companion object {

        var appDb: AutolibDatabase? = null


        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }


        fun getEtatById(context: Context, id: Int): EtatTache {
            appDb = initializeDB(context)
            return appDb!!.etatDao().getEtatById(id)
        }

        fun getAllEtat(context: Context):List<EtatTache>{
            appDb = initializeDB(context)
            return appDb!!.etatDao().getAllEtats()
        }


    }
}