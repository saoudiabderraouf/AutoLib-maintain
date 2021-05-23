package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.clovertech.autolib.model.Agent
import com.clovertech.autolib.repository.ProfilRepo

class ProfilViewModel : ViewModel() {

    fun deleteProfil(context: Context, profil: Agent) {
        ProfilRepo.deleteProfil(context, profil)
    }

    fun getProfilById(context: Context, id: Int): Agent {
        return ProfilRepo.getProfilById(context, id)
    }

    fun updateProfil(context: Context, profil: Agent) {
        ProfilRepo.updateProfil(context, profil)
    }


}