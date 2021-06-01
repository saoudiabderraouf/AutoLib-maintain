package com.clovertech.autolib.cache.converter

import androidx.room.TypeConverter
import com.clovertech.autolib.model.Materiel
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
   
    @TypeConverter
    fun dateToTimestamp(listMateriel: List<Materiel>?): String? {
        var listInString: String=""
        if (listMateriel != null) {
            listInString=listMateriel.joinToString ("-")
        }
        return listInString
    }
}