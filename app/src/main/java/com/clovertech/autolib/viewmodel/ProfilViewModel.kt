package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Utilisateur
import com.clovertech.autolib.repository.ProfilRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfilViewModel : ViewModel() {
    val ResponseProfil: MutableLiveData<Response<Utilisateur>> = MutableLiveData()


    fun getThisProfil(id: Int) {
        viewModelScope.launch {
            val response: Response<Utilisateur> = ProfilRepo.getProfil(id)
            ResponseProfil.value = response
        }
    }


}