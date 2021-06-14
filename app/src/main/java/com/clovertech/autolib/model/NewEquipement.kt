package com.clovertech.autolib.model

import com.google.gson.annotations.Expose

data class NewEquipement(
    @Expose var description: String,
    @Expose var quantity: Int,
    @Expose var equipment: String,
    @Expose var taskUUID: String

) {}