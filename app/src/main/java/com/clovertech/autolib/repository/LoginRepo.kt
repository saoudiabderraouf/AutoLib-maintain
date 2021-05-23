package com.clovertech.autolib.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.model.Token
import com.clovertech.autolib.network.client.AuthApiClient
import com.clovertech.autolib.network.service.AuthApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepo {
    suspend fun userLogin(login: Login) : Response<Token> {
        return AuthApiClient.authApiService.getUserByInfo(login)
    }
}