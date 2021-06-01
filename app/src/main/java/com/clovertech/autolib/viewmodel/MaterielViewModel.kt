package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.repository.MaterielRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class MaterielViewModel : ViewModel() {
    val ResponseMateriel: MutableLiveData<Response<Materiel>> = MutableLiveData()
    val ResponseToken: MutableLiveData<Response<Token>> = MutableLiveData()
    val ResponseListMateriel: MutableLiveData<Response<List<Materiel>>> = MutableLiveData()
    fun getListMateriel(id: Int) {
        viewModelScope.launch {
            val response: Response<List<Materiel>> = MaterielRepo.getAllEquipments()
            ResponseListMateriel.value = response

        }
    }

    fun addMateriel(materiel: Materiel) {
        viewModelScope.launch {
            val response: Response<Materiel> = MaterielRepo.addMateriel(materiel)
            ResponseMateriel.value = response
        }
    }

    fun getMaterielById(id: Int) {
        viewModelScope.launch {
            val response: Response<Materiel> = MaterielRepo.getMaterielById(id)
            ResponseMateriel.value = response
        }
    }

    fun updateMateriel(id: Int, materiel: Materiel) {
        viewModelScope.launch {
            val response: Response<Materiel> = MaterielRepo.updateMateriel(id, materiel)
            ResponseMateriel.value = response
        }
    }

    fun deleteMateriel(id: Int) {
        viewModelScope.launch {
            val response: Response<Token> = MaterielRepo.deleteMateriel(id)
            ResponseToken.value = response
        }
    }

}