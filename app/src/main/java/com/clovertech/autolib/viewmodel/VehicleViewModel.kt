package com.clovertech.autolib.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Vehicle
import com.clovertech.autolib.network.client.VehicleApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleViewModel: ViewModel() {

    var vehicle = Vehicle(0,"",""
        ,"","","","","",""
    ,"",0,"",0F,0F,"")

    fun getVehicleById(idVehicle: Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response = VehicleApiClient.vehicleService.getVehicles(idVehicle)
            if (response.isSuccessful){
                if(response.body() != null)
                    vehicle = response.body()!!
            }
        }
    }

}