package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.EquipementToken
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.NewEquipement
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.repository.EquipmentRepo
import com.clovertech.autolib.repository.LoginRepo
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class EquipmentViewModel : ViewModel (){
    val Response: MutableLiveData<Response<EquipementToken>> = MutableLiveData()
    fun addMateriel(newEquipement: NewEquipement) {
        viewModelScope.launch {
            val response: Response<EquipementToken> = EquipmentRepo.addNewEquipment(newEquipement)
            Response.value = response
        }

    }

}