package com.clovertech.autolib.model

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("idVehicle") val idVehicle: Int,
    @SerializedName("unitPricePerHour") val unitPricePerHour: String,
    @SerializedName("unitPricePerDay") val unitPricePerDay: String,
    @SerializedName("vehicleType") val vehicleType: String,
    @SerializedName("vehiclebrand") val vehiclebrand: String,
    @SerializedName("vehiclemodel") val vehiclemodel: String,
    @SerializedName("availibility") val availibility: String,
    @SerializedName("image") val image: String,
    @SerializedName("vehicleColor") val vehicleColor: String,
    @SerializedName("registrationNumber") val registrationNumber: String,
    @SerializedName("idBorne") val idBorne: Int,
    @SerializedName("fuelType") val fuelType: String,
    @SerializedName("longitude") val longitude: Float,
    @SerializedName("latitude") val latitude: Float,
    @SerializedName("chassisNumber") val chassisNumber: String?,
    var firstname: String = "",
    var lastname: String = "",
    var rentaldate: String = "",
    var idrental: Int = 0,
    var availibledate: String = "")