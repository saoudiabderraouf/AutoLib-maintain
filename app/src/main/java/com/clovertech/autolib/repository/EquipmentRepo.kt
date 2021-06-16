package com.clovertech.autolib.repository

import com.clovertech.autolib.model.Equipement
import com.clovertech.autolib.model.EquipementToken
import com.clovertech.autolib.model.NewEquipement
import com.clovertech.autolib.network.client.EquipmentApiClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

object EquipmentRepo {

        suspend fun getAllEquipments(): Response<List<Equipement>> {
            return EquipmentApiClient.equipmentApiService.getAllEquipment()
        }

        suspend fun addNewEquipment(newEquipement: NewEquipement): Response<EquipementToken> {
            return EquipmentApiClient.equipmentApiService.addNewUsedEquipment(newEquipement)
        }
}