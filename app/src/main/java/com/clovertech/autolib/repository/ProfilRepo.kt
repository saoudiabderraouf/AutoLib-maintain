package com.clovertech.autolib.repository

import android.content.Context
import com.clovertech.autolib.cache.db.AutolibDatabase
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.model.Utilisateur
import com.clovertech.autolib.network.client.UserApiClient
import retrofit2.Response

class ProfilRepo {
    companion object {

        var appDb: AutolibDatabase? = null


        fun initializeDB(context: Context): AutolibDatabase {
            return AutolibDatabase.getDatabaseClient(context)
        }


        fun deleteProfil(context: Context, profil: Agent) {
            appDb = initializeDB(context)
            appDb!!.profilsDao().deleteProfil(profil)

        }

        fun getProfilById(context: Context, id: Int): Agent {
            appDb = initializeDB(context)
            return appDb!!.profilsDao().getThisProfil(id)
        }

        fun updateProfil(context: Context, profil: Agent) {
            appDb = initializeDB(context)
            return appDb!!.profilsDao().updateProfil(profil)
        }
         suspend fun getProfil(id:Int): Response<Utilisateur>{
             return UserApiClient.utilisateurApiService.getUserById(id)

         }


    }
}