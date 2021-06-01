package com.clovertech.autolib.repository

import com.clovertech.autolib.model.Equipement
import com.clovertech.autolib.model.UsedEquipment
import com.clovertech.autolib.network.client.EquipmentApiClient
import retrofit2.Response

class EquipmentRepo {
    companion object {
        suspend fun getAllEquipments(): Response<List<Equipement>> {
            return EquipmentApiClient.equipmentApiService.getAllEquipment()
        }

        suspend fun addNewEquipment(usedEquipment: UsedEquipment): Response<UsedEquipment> {
            return EquipmentApiClient.equipmentApiService.addNewUsedEquipment(usedEquipment)
        }

    }
}