package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.repository.TacheRepo

class TacheViewModel : ViewModel() {
    fun insertTache(context: Context, tache: Tache) {
        TacheRepo.insertTache(context, tache)
    }

    fun insertAllTaches(context: Context, taches: List<Tache>) {
        TacheRepo.insertAllTaches(context, taches)
    }

    fun getAllTaches(context: Context): LiveData<List<Tache>>? {
        return TacheRepo.getAllTaches(context)
    }
}