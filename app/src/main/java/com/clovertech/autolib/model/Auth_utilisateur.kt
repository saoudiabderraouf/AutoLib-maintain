package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_users")
data class Auth_utilisateur (
    @PrimaryKey (autoGenerate = true) var idUtilisateur: Int,
    @ColumnInfo (name = "type") var type: String,
    @ColumnInfo (name = "adresseEmail")var adresseEmail: String,
    @ColumnInfo (name = "motDePasse")var motDePasse: String
)