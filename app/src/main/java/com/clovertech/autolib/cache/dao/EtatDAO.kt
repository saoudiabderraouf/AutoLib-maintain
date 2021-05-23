package com.clovertech.autolib.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.clovertech.autolib.model.EtatTache

@Dao
interface EtatDAO {
   /* @Query("SELECT * from etatTache")
    fun getAllEtats(): List<EtatTache>*/
   /* @Query("SELECT * FROM etatTache WHERE idEtat =:idEtat")
    fun getEtatById(idEtat: Int): EtatTache*/
}