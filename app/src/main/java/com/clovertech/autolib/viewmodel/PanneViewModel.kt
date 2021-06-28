package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.model.TacheModel
import com.clovertech.autolib.repository.PanneRepo
import com.clovertech.autolib.repository.TacheRepo
import kotlinx.coroutines.launch
import retrofit2.Response


class PanneViewModel : ViewModel(){
    val ResponsePanne: MutableLiveData<Response<Panne>> = MutableLiveData()
    fun getAllPannes(context: Context): LiveData<List<Panne>>? {
        return PanneRepo.getAllPannes(context)
    }
    fun addPanne(panne: Panne){
        viewModelScope.launch {
            val response = PanneRepo.addPanne(panne)
            ResponsePanne.value= response
        }

    }

}