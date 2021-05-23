package com.clovertech.autolib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.repository.LoginRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val Response: MutableLiveData<Response<Token>> = MutableLiveData()

    fun onLoginButtonClick(login: Login) {
        viewModelScope.launch {
            val response: Response<Token> = LoginRepo().userLogin(login)
            Response.value = response
        }

    }

}