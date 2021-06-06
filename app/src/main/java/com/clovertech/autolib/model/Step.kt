package com.clovertech.autolib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class Step(
    var idStep: Int,
    var step: String,
    var completed: Boolean,
    var isSynchronized: Int = 0

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "team_id")
    var stepIdInt: Int? = null
}