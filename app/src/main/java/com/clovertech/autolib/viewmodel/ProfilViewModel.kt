package com.clovertech.autolib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.User
import com.clovertech.autolib.repository.ProfilRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfilViewModel : ViewModel() {
    val responseProfil: MutableLiveData<Response<User>> = MutableLiveData()


    fun getThisProfil(id: Int) {
        viewModelScope.launch {
            val response: Response<User> = ProfilRepo.getProfil(id)
            responseProfil.value = response
        }
    }


}