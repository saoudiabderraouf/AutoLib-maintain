package com.clovertech.autolib.ui.notifications

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Notifications")
data class Notif(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("idTask")
    var idTask: Int,
    //var titre: String,
    //var date: String,
    //var image: Int,
    @SerializedName("read")
    var lu :Boolean
    )