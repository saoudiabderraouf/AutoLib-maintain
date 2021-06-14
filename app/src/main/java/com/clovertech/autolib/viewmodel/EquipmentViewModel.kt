package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.EquipementToken
import com.clovertech.autolib.model.NewEquipement
import com.clovertech.autolib.repository.EquipmentRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class EquipmentViewModel : ViewModel() {
    val Response: MutableLiveData<Response<EquipementToken>> = MutableLiveData()
    fun addMateriel(newEquipement: NewEquipement) {
        viewModelScope.launch {
            val response: Response<EquipementToken> = EquipmentRepo.addNewEquipment(newEquipement)
            Response.value = response
        }

    }

}