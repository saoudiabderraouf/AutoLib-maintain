package com.clovertech.autolib.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Auth_utilisateur (
    @PrimaryKey (autoGenerate = true) var idUtilisateur: Int,
    @ColumnInfo (name = "type") val type: String,
    @ColumnInfo (name = "adresseEmail")var adresseEmail: String,
    @ColumnInfo (name = "motDePasse")val motDePasse: String
)