package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.model.TacheModel
import com.clovertech.autolib.model.TaskModelToken
import com.clovertech.autolib.repository.TacheRepo
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class TacheViewModel : ViewModel() {
    val ResponseTacheById: MutableLiveData<Response<List<Tache>>> = MutableLiveData()
    val ResponseTacheModel: MutableLiveData<Response<TacheModel>> = MutableLiveData()

    var taskModel: TacheModel= TacheModel(1,"", emptyList())
    var task: Tache= Tache(0,0,"","",0, Date(),Date(), TaskModelToken(0,""), emptyList(),"")


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

    fun getTacheModelid(id: Int) {
        viewModelScope.launch {
            val response: Response<TacheModel> = TacheRepo.getTacheModelById(id)
            ResponseTacheModel.value = response

        }
    }

}