package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.repository.TacheRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class TacheViewModel : ViewModel() {
    val ResponseTacheById: MutableLiveData<Response<List<Tache>>> = MutableLiveData()
    fun insertTache(context: Context, tache: Tache) {
        TacheRepo.insertTache(context, tache)
    }

    fun insertAllTaches(context: Context, taches: List<Tache>) {
        TacheRepo.insertAllTaches(context, taches)
    }

    fun getAllTaches(context: Context): LiveData<List<Tache>>? {
        return TacheRepo.getAllTaches(context)
    }

    fun getTacheIdAgent(id: Int) {
        viewModelScope.launch {
            val response: Response<List<Tache>> = TacheRepo.getTacheIdAgent(id)
            ResponseTacheById.value = response

        }
    }

}