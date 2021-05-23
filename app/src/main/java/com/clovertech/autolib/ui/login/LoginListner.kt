package com.clovertech.autolib.ui.login

import androidx.lifecycle.LiveData

interface LoginListner {

    fun onStarted()

    fun onSuccess(loginResponseFromLoginRepository: LiveData<String>)

    fun onFailure(message: String)
}