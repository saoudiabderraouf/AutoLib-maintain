package com.clovertech.autolib.model

import java.util.*

data class Panne (
     val idPanne : Int,
     val dateNotification: Date,
     val dateReparationPanne: Date,
     val etat: String,
     val Description : String,
     val numChassis: Int,
     val idAgentEnvoyeNotif: Int,
     val idAgentTraitePanne: Int,
     val niveauSecurity: String
 )