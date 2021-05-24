package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.model.Utilisateur
import com.clovertech.autolib.repository.LoginRepo
import com.clovertech.autolib.repository.ProfilRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfilViewModel : ViewModel() {
    val ResponseProfil: MutableLiveData<Response<Utilisateur>> = MutableLiveData()

    fun deleteProfil(context: Context, profil: Agent) {
        ProfilRepo.deleteProfil(context, profil)
    }

    fun getProfilById(context: Context, id: Int): Agent {
        return ProfilRepo.getProfilById(context, id)
    }

    fun updateProfil(context: Context, profil: Agent) {
        ProfilRepo.updateProfil(context, profil)
    }
    fun getThisProfil(id : Int){
        viewModelScope.launch {
            val response: Response<Utilisateur> = ProfilRepo.getProfil(id)
            ResponseProfil.value = response
        }
    }


}